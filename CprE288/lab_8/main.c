

/**
 * main.c
 */
#include "lcd.h"
#include "adc.h"
#include "Timer.h"
#include "math.h"

int IR_raw_val = 0;
int est_dist = 0;
int i = 0;
int index = 1;
int sum = 0;
int avg = 0;
int main(void)
{
    timer_init();
    lcd_init();
    adc_init();
	while(1){
//	    for(i = 0; i<16;i++){
//	        sum += adc_read();
//	    }
//	    IR_raw_val = sum / 16;
//	    sum = 0;
	    IR_raw_val = adc_read();
	   // est_dist = (11243 * pow(IR_raw_val, -0.7593)) - 14;
	    est_dist = (21816 * pow(IR_raw_val, -0.8520)) - 19;
	    lcd_printf("Raw value: %d\nEstimated Distance: %d", IR_raw_val, est_dist);
	    if(IR_raw_val > 1000){
	        timer_waitMillis(500);
	    }
	    timer_waitMillis(200);
}
}
