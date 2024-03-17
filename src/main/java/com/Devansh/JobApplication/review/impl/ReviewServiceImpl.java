package com.Devansh.JobApplication.review.impl;

import com.Devansh.JobApplication.companies.Company;
import com.Devansh.JobApplication.companies.companyService;
import com.Devansh.JobApplication.review.Review;
import com.Devansh.JobApplication.review.ReviewRepo;
import com.Devansh.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final companyService companyservice;
    public ReviewServiceImpl(ReviewRepo reviewRepo , companyService companyservice) {

        this.reviewRepo = reviewRepo;
        this.companyservice = companyservice;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepo.findAllByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyservice.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }else return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepo.findAllByCompanyId(companyId);
        return reviews.stream().filter(k-> k.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyservice.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyservice.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepo.save(updatedReview);
            return true;
        }else return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if( companyservice.getCompanyById(companyId) != null && reviewRepo.existsById(reviewId)){
            Review review = reviewRepo.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyservice.updateCompany(companyId , company);
            reviewRepo.deleteById(reviewId);
            return true;
        }else return false;
    }
}
