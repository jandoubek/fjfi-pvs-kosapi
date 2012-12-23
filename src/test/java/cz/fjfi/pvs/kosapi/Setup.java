/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fjfi.pvs.kosapi;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author edariedl
 */
@RunWith(Suite.class)
@SuiteClasses({ AppTest.class })
public class Setup {
    @Before
    public static void setUp(){
        System.out.println("setup class");
    }
}
