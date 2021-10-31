package com.Exam.ExamPortal.Controller;

import com.Exam.ExamPortal.Model.Exam.Category;
import com.Exam.ExamPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryService categorySer;

    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return  ResponseEntity.ok(this.categorySer.addCategory(category));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(this.categorySer.getCategory(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categorySer.getCategories());
    }

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categorySer.updateCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public void  deleteCategory(@PathVariable("categoryId") Long categoryId){
        this.categorySer.deleteCategory(categoryId);
    }


}
