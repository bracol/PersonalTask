package com.example.waniltonfilho.personaltasks.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.service.CategoryService;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 12/03/2016.
 */
public class BaseActivity extends AppCompatActivity{


    public void setupToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }

    public void setupFloatingButton(FloatingActionButton fab){

    }

    public List<Category> getCategories(){
        List<Category> categories = CategoryService.findAll();
        if(categories.size() == 0) {
            CategoryService.save(getCategory("Alimentação", R.drawable.food));
            CategoryService.save(getCategory("Café", R.drawable.coffee));
            CategoryService.save(getCategory("Carro", R.drawable.car));
            CategoryService.save(getCategory("Celular", R.drawable.cell));
            CategoryService.save(getCategory("Computador", R.drawable.cd));
            CategoryService.save(getCategory("Jogos", R.drawable.game));
            CategoryService.save(getCategory("Negócios", R.drawable.business));
            CategoryService.save(getCategory("Shopping", R.drawable.shopping));
            CategoryService.save(getCategory("Esporte", R.drawable.sport));
            CategoryService.save(getCategory("Trabalho", R.drawable.mala));
            CategoryService.save(getCategory("Transporte", R.drawable.bus));
            CategoryService.save(getCategory("Viagem", R.drawable.travel));
            categories = CategoryService.findAll();
        }
        return categories;
    }

    private Category getCategory(String nome, long category_icon){
        Category category = new Category();
        category.setName(nome);
        category.setCategory_icon(category_icon);
        return category;
    }
}
