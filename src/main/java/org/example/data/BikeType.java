/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.example.data;

/**
 *
 * @author makar
 */
public enum BikeType {
    MOUNTAIN("mountain"), TREK("treking"), BMX("bmx"), ALL("all");

    
    private final String name;

    private BikeType(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
