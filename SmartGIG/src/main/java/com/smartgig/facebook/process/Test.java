package com.smartgig.facebook.process;

public class Test {
	public static void main(String[] args){
		
		String message = "i love pineapple. i love mayonaise. i like programming. i like skirt. i like dress. i love foooood. i like dance. cap is cute. jayr's fashion style is what i appreciate. i like lucy hale. she's sooo cool. so does olivia gonzales and maja salvador. lucy hale's pretty little liars is interesting. Harry potter? that story is interesting tooo.. i love GOT7 and jungkook. i love jayr. i'm his fan. :D";
		Preprocessing p = new Preprocessing();
		p.processRelevant(message, "ojasdinfn");
	}
}
