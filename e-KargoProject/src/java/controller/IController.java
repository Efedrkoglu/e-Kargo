/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author Efe
 */

public interface IController <E>{
    public ArrayList<E> select();
    public void insert();
    public void update();
    public void delete(E entity);
}
