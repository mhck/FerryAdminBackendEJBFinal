/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.controller;

import ferry.eto.DataAccessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper
 */
public class BackendRunner {
    
    public static void main(String[] args) {
        FerryAdminManager manager = new FerryAdminManager();
        try {
            System.out.println("Ferries: " + manager.showFerries().isEmpty());
        } catch (DataAccessException ex) {
            Logger.getLogger(BackendRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
