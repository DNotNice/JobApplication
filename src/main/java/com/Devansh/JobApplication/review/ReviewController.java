package com.Devansh.JobApplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController{
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    private ResponseEntity<List<Review>> getAllReviews(@PathVariable  Long companyId){
        List<Review> reviews =  reviewService.getAllReview(companyId);
        return new ResponseEntity<>(reviews , HttpStatus.ACCEPTED);
    }
    @PostMapping("/reviews")
    private ResponseEntity<String> addReview(@PathVariable Long companyId , @RequestBody Review review){
           boolean res =  reviewService.addReview(companyId , review);
           return res ?  new ResponseEntity<>("Review  Added Successfully" , HttpStatus.OK)
                   : new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable long companyId , @PathVariable Long reviewId){
        return new ResponseEntity<>( reviewService.getReview(companyId , reviewId)  , HttpStatus.OK );
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long companyId , @PathVariable long reviewId , @RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(companyId ,reviewId , review);

        return isUpdated ? new ResponseEntity<>("Review Updated Successfully" , HttpStatus.OK)
                : new ResponseEntity<>("Review not updated" , HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public  ResponseEntity<String> deleteReview(@PathVariable long companyId , @PathVariable long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId , reviewId );
        return isDeleted ? new ResponseEntity<>("Review deleted successfully" , HttpStatus.OK)
                : new ResponseEntity<>("Review not deleted" , HttpStatus.NOT_FOUND);
    }
}
