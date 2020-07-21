package com.gojek.solution;

import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gojek.solution.parkinglot.Router;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class MainTest{
	
	@Mock
	Router router;
	
	@Before
	public void  setUp() {
		router = Mockito.spy(new Router(1,"abc"));
	}

    @Test
    public void testMain() {
    	String[] args = {"abc"};
//    	doNothing().when(router)
//        .execute();
    	Main.main(args);
    }
}
