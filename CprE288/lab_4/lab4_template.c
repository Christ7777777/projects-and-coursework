/**
 * lab4_template.c
 * 
 * Template file for CprE 288 lab 4
 *
 * @author Zhao Zhang, Chad Nelson, Zachary Glanz
 * @date 08/14/2016
 */

#include "button.h"
#include "Timer.h"
#include "lcd.h"
#include "cyBot_uart.h"  // Functions for communiticate between CyBot and Putty (via UART)
                         // PuTTy: Buad=115200, 8 data bits, No Flow Control, No Party,  COM1


#define REPLACEME 0



int main(void) {
    cyBot_uart_init();
	button_init();
	lcd_init();
	

//char str[1000];
//	int i;
	int buttonNumber;
	int count = 0;
	while(1)
	{
//Part 2
	    buttonNumber = button_getButton();
	  // lcd_printf("Button: %d", buttonNumber);
//1Part 3
    if(buttonNumber == 4 && count == 0){
        lcd_printf("%d", buttonNumber);
        cyBot_sendByte('4');
        count++;
	}
    else{
        count = 0;
    }
    if(buttonNumber == 3 && count == 0){
           lcd_printf("%d", buttonNumber);
           cyBot_sendByte('3');
           count++;
       }
    else{
        count = 0;
    }
    if(buttonNumber == 2 && count == 0){
           lcd_printf("%d", buttonNumber);
           cyBot_sendByte('2');
           count++;
       }
    else{
        count = 0;
    }
    if(buttonNumber == 1 && count == 0){
           lcd_printf("%d", buttonNumber);
           cyBot_sendByte('1');
           count++;
       }
    else{
        count = 0;
    }
	}
	
}
