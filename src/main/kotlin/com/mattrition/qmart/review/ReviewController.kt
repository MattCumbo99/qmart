package com.mattrition.qmart.review

import com.mattrition.qmart.review.dto.CreateReviewRequest
import com.mattrition.qmart.review.dto.ReviewDto
import com.mattrition.qmart.user.UserRole
import jakarta.annotation.security.RolesAllowed
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/reviews")
class ReviewController(
    private val reviewService: ReviewService,
) {
    @GetMapping("/listing/{listingId}")
    fun getReviews(
        @PathVariable listingId: UUID,
    ) = reviewService.getListingReviews(listingId)

    @GetMapping("/user/{userId}")
    fun getUserReviews(
        @PathVariable userId: UUID,
    ) = reviewService.getReviewsByUser(userId)

    @PostMapping("/listing/{listingId}")
    @RolesAllowed(UserRole.USER)
    fun createReview(
        @PathVariable listingId: UUID,
        @RequestBody req: CreateReviewRequest,
    ): ResponseEntity<ReviewDto> {
        val review = reviewService.createReview(req, listingId)

        return ResponseEntity(review, HttpStatus.CREATED)
    }
}
