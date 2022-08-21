package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:21
 */
public interface PetService {
    List<Pet> queryAllPets();
    List<Pet> quearyPetsByTypeAndGender(String type, String gender);
    int addPet(Pet pet);
    int updateisadopt(String id);
    static LinkedList<Integer> res=new LinkedList<>();
    public static void main(String[] args) {
        LinkedList<Integer> list=new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        traverse(list);
        System.out.println(res);
    }

    static void traverse(LinkedList<Integer> list) {
        if (list.size()<=2){
            return;
        }
        LinkedList<Integer> temp=new LinkedList<>();
        for (int i = 0, j=list.size()-1; i<j; i++, j--) {
            temp.add(list.get(i)+list.get(j));
        }
        list=temp;
        traverse(list);
    }


}
