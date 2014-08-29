uniform float iOvertoneVolume;
uniform float iBeat;

void main(void){
  vec2 uv = gl_FragCoord.xy / iResolution.xy;
  vec4 color = vec4(0.0);

  if((uv.x > 0.45) && (uv.x < 0.55) &&
     (uv.y > 0.45) && (uv.y < 0.55)){

    if(iBeat == 1.0){
      color = vec4(1.0 * iBeat);
    }
    else{
      color = vec4(0.3 * iGlobalTime*0.1);
    }

}

  gl_FragColor = color;
}
