package com.mattrition.qmart.review

import com.mattrition.qmart.auth.CustomUserDetails
import com.mattrition.qmart.exception.BadRequestException
import com.mattrition.qmart.exception.ForbiddenException
import com.mattrition.qmart.exception.NotFoundException
import com.mattrition.qmart.itemlisting.ItemListing
import com.mattrition.qmart.itemlisting.ItemListingRepository
import com.mattrition.qmart.review.dto.CreateReviewRequest
import com.mattrition.qmart.review.dto.ReviewDto
import com.mattrition.qmart.review.mapper.ReviewMapper
import com.mattrition.qmart.user.UserRepository
import com.mattrition.qmart.util.authPrincipal
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrElse

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val itemListingRepository: ItemListingRepository,
    private val userRepository: UserRepository,
) {
    /** Retrieves all reviews left on an item listing sorted newest first. */
    fun getListingReviews(listingId: UUID) =
        reviewRepository.findReviewsByListingId(listingId).map { review ->
            ReviewMapper.toDto(review)
        }

    /** Retrieves all reviews left by a user sorted newest first. */
    fun getReviewsByUser(userId: UUID) = reviewRepository.findReviewsByUserId(userId).map { ReviewMapper.toDto(it) }

    /** Saves a review to the database. */
    fun createReview(
        request: CreateReviewRequest,
        listingId: UUID,
    ): ReviewDto {
        val listing =
            itemListingRepository.findById(listingId).getOrElse {
                throw NotFoundException("Listing $listingId not found.")
            }

        val authUser = authPrincipal()

        enforceCreationRules(authUser, request, listing)

        val reviewAuthor = userRepository.findById(authUser.id).get()

        val reviewEntity =
            Review(
                listingId = listingId,
                user = reviewAuthor,
                body = request.body,
                score = request.score,
            )

        val saved = reviewRepository.save(reviewEntity)

        return ReviewMapper.toDto(saved)
    }

    /** Runs a bunch of checks to ensure the authenticated user can create the review. */
    private fun enforceCreationRules(
        authUser: CustomUserDetails,
        request: CreateReviewRequest,
        listing: ItemListing,
    ) {
        val userId = authUser.id

        // 1. User must not own listing
        if (userId == listing.sellerId) {
            throw ForbiddenException("User $userId owns listing ${listing.id}")
        }

        // 2. User must not have placed a previous review on the listing
        val exists = reviewRepository.hasUserReviewedListing(userId, listing.id!!)
        if (exists) {
            throw ForbiddenException("User $userId already has review for ${listing.id}")
        }

        // 3. Score must be between 1 and 5
        if (request.score !in 1..5) {
            throw BadRequestException("Score must be between 1 and 5. Actual: ${request.score}")
        }
    }
}
