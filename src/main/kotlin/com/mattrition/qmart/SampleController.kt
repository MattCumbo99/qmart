package com.mattrition.qmart

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @RequestMapping("/sample", method = [RequestMethod.GET])
    @CrossOrigin
    @ResponseBody
    fun sample(): String = "The sample has been extracted!"
}