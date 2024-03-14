package com.Devansh.JobApplication.review.impl;

import com.Devansh.JobApplication.review.Review;
import com.Devansh.JobApplication.review.ReviewRepo;
import com.Devansh.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepo reviewRepo;

    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> allReviews = reviewRepo.findAllByCompanyId(companyId);
        return allReviews;
    }
}
