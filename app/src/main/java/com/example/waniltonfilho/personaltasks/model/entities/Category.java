package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 24/03/2016.
 */
public class Category implements Parcelable {

    private Long id;
    private String name;
    private long category_icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(long category_icon) {
        this.category_icon = category_icon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeLong(this.category_icon);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.category_icon = in.readLong();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public static List<Category> getCategories(){
        List<Category> categories = CategoryService.findAll();
        if(categories.size() == 0) {
            CategoryService.save(addCategory("Alimentação", R.drawable.food));
            CategoryService.save(addCategory("Café", R.drawable.coffee));
            CategoryService.save(addCategory("Carro", R.drawable.car));
            CategoryService.save(addCategory("Celular", R.drawable.cell));
            CategoryService.save(addCategory("Computador", R.drawable.cd));
            CategoryService.save(addCategory("Jogos", R.drawable.game));
            CategoryService.save(addCategory("Negócios", R.drawable.business));
            CategoryService.save(addCategory("Shopping", R.drawable.shopping));
            CategoryService.save(addCategory("Esporte", R.drawable.sport));
            CategoryService.save(addCategory("Trabalho", R.drawable.mala));
            CategoryService.save(addCategory("Transporte", R.drawable.bus));
            CategoryService.save(addCategory("Viagem", R.drawable.travel));
            categories = CategoryService.findAll();
        }
        return categories;
    }

    private static Category addCategory(String nome, long category_icon){
        Category category = new Category();
        category.setName(nome);
        category.setCategory_icon(category_icon);
        return category;
    }
}
