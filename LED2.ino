#include <SoftwareSerial.h> //Librería que permite establecer comunicación serie en otros pins
 
//Aquí conectamos los pins RXD,TDX del módulo Bluetooth.
SoftwareSerial BT(10,11); //10 RX, 11 TX.

char datosRecibidos = ' ';

#define led 13
 
void setup()
{
  BT.begin(115200); //Velocidad del puerto del módulo Bluetooth
  Serial.begin(115200); //Abrimos la comunicación serie con el PC y establecemos velocidad
  pinMode(led, OUTPUT);
}
 
void loop()
{
  if(BT.available())
  {

    datosRecibidos = BT.read();
    Serial.write(datosRecibidos);
  }
 
  if(Serial.available())
  {
     BT.write(Serial.read());
  }

  if ( datosRecibidos == '1' )
  {
      digitalWrite(led, HIGH);
  }
  else
  {
     digitalWrite(led, LOW);
  }
}