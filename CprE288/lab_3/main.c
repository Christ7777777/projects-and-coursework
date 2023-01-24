
/**
 * main.c
 */
#include "lcd.h"
#include "Timer.h"
#include "cyBot_Scan.h"
#include "cyBot_uart.h"
#include "resetSimulation.h"
#include "movement.h"
#include "open_interface.h"
#include <math.h>
#include <stdio.h>


typedef struct d{
    float data;
    int angle;
    int radialWidth;
    int width;
    int startAngle;
    int endAngle;
    int rawData;
    int estimatedDistance;
}object_t;

object_t objects[10];
float data[91];
int dist[91];
int startIndex = 0;
int endIndex = 0;
int currentRadialSize = 0;
int radialAngle = 0;
double otherAngle = 0;
int minSize = 0;
int minIndex = 0;
int index = 0;
int found = 0;
int notFound = 0;
int total = 0;
int measure = 0;
char str[1000];
int main(void)
{
    timer_init();
    lcd_init();
    oi_t *sensor = oi_alloc();
    oi_init(sensor);
    cyBot_uart_init();
    cyBOT_init_Scan(0b0111);


    right_calibration_value = 256375;
    left_calibration_value = 1251250;
    char ch = cyBot_getByte();
    int i;
    int j;
    int a;
    int b;
   int c;

    cyBOT_Scan_t scan;

    if (ch == 'm')
    {
        char* init = "Angle\tDistance(IR in cm)\tDistance(Ping in cm)\n\r";
                int k;
                for(k = 0; k<strlen(init);k++){
                    cyBot_sendByte(init[k]);
                }
        for(i = 0; i<=180; i+= 2){
            cyBOT_Scan(i, &scan);
            data[i/2] = scan.sound_dist;

            dist[i/2] = scan.IR_raw_val/10;

            sprintf(str, "%d\t%d\t\t\t\t%d\n\r",i, scan.IR_raw_val/10, (int)scan.sound_dist);
                      for(j = 0; j <strlen(str);j++){
                          cyBot_sendByte(str[j]);
                      }
        }
        for(j = 0 ; j<=90;j++){
        if(dist[j] > 100){
            if(found == 0){
               objects[index].startAngle = 2*j;
              // objects[index].angle = 2*j;
               startIndex = data[j];
//               objects[index].rawData = dist[j]*10;
//               objects[index].estimatedDistance = (objects[index].rawData - 3351)/(-64.31);
                found++;
            }
            else{
                objects[index].data = data[j];
                objects[index].endAngle = 2*(j+found);

                found++;
            }
        }
        if(found == 4){
                           objects[index].rawData = dist[j]*10;
                           objects[index].estimatedDistance = (11243 *pow(objects[index].rawData, -0.7693)-15);
        }
        if(dist[j] < 100){
            if(found >=4){
                objects[index].endAngle = 2*j;
                objects[index].radialWidth = objects[index].endAngle - objects[index].startAngle;
                objects[index].angle = 2*(j-(found/2));
                endIndex = data[j];

//                otherAngle = -2 * (double)objects[index].data * (double)objects[index].data * cos(((double)objects[index].radialWidth)*(3.14/180));
//                objects[index].width = sqrt(2*((double)objects[index].data *(double) objects[index].data) + otherAngle);
                otherAngle = (180 - objects[index].radialWidth)/2;
                objects[index].width = 2*cos((double)((otherAngle)*(3.14/180)))*(double)objects[index].data;
                index++;
                found = 0;
            }
            if(found < 4 && found > 0){
                found = 0;
            }
        }


        }

        char str_new[1000];
        char* init_next = "\n\rObject#\tAngle\tDistance(in cm)\t\tRadial Width\t\tWidth( in cm)\n\r";
        for(a = 0; a<strlen(init_next); a++){
            cyBot_sendByte(init_next[a]);
        }
        int minSize = objects[0].width;
        for(c = 0; c<index;c++){
            sprintf(str_new, "\n\r%d\t%d\t\t%d\t\t%d\t\t\t%d\n\r", c+1, (objects[c].angle),(int)objects[c].data, objects[c].radialWidth, objects[c].width);
            for(b = 0; b<strlen(str_new);b++){
                cyBot_sendByte(str_new[b]);
            }
            if(objects[c].width < minSize){
                minSize = objects[c].width;
                minIndex = c;
            }
        }
        cyBOT_Scan(0, &scan);
        cyBOT_Scan(objects[minIndex].angle, &scan);
        lcd_printf("Quantized value: %d\n Raw Value: %d", objects[minIndex].estimatedDistance, objects[minIndex].rawData);
    }

    oi_free(sensor);

    return 0;
}
