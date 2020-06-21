//Afinic cipher parameters
int a=7, b=4;


char* cipher(char* message){
  char* result = new char[strlen(message) + 1];
  for(int i = 0; message[i]; i++){
    result[i] = 'a' + (((message[i] - 'a') * a + b) % 26);
  }
  result[strlen(message)] = '\0';
  return result;
}

char* decipher(char* message){
  char* result = new char[strlen(message) + 1];
  int x = 1;
  while((a*x) % 26 != 1){
    x++;
  }
  for(int i = 0; message[i]; i++){
    result[i] = 'a' + ((x * (message[i] - 'a' - b + 26)) % 26);
  }
  result[strlen(message)] = '\0';
  return result;
}

void actionGetAB(){
  Serial.println("A: " + (String)a + " B: " + (String)b);
}

void actionSetAB(String message){
   int separatorIndex = message.indexOf("/");
   String newA = message.substring(0, separatorIndex);
   String newB = message.substring(separatorIndex + 1, message.length());
   a = newA.toInt();
   b = newB.toInt();
}

void actionEncode(String message){
    char charMessage[message.length() + 1];
    message.toCharArray(charMessage, message.length()+1);    
    char* encoded = cipher(charMessage); 
    Serial.println(encoded);
    delete encoded;
}

void actionDecode(String message){
    char charMessage[message.length() + 1];
    message.toCharArray(charMessage, message.length()+1);
    char* decoded = decipher(charMessage);
    Serial.println(decoded);
    delete decoded;
}

void setup() {
  Serial.begin(9600);
}

void loop() {
  if(Serial.available() > 0){
     String incomingRequest = Serial.readStringUntil('\n');
   
     int separatorIndex = incomingRequest.indexOf("#");
     String prefix = incomingRequest.substring(0,separatorIndex);
     String message = incomingRequest.substring(separatorIndex + 1, incomingRequest.length());

     if(prefix == "00"){
        actionGetAB();
     }
     else if(prefix == "01"){
        actionSetAB(message);
     }
     else if(prefix == "10"){
        actionEncode(message);
     }
     else if(prefix == "11"){
        actionDecode(message);
     }
     else {
        Serial.println("request: " + incomingRequest + " isn't supported");
     }
  } 
}
