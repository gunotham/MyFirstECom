package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
