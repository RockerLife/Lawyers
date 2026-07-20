function capitalizeSentences(m, i) {

	var capText = m.value;
	/*//alert(capText);
	var allCaps = i
	capText = capText.toLowerCase();
	if (allCaps == 1 || allCaps == true)
	{

		capText = capText.replace(/\n/g, ". [-<br>-] ");
		var wordSplit = ' ';

	} else {
		capText = capText.replace(/\.\n/g, ".[-<br>-]. ");
		capText = capText.replace(/\.\s\n/g, ". [-<br>-]. ");
		var wordSplit = '. ';
	}

	var wordArray = capText.split(wordSplit);

	var numWords = wordArray.length;
	//alert(numWords);

	for(x=0;x<numWords;x++) {

		wordArray[x] = wordArray[x].replace(wordArray[x].charAt(0),wordArray[x].charAt(0).toUpperCase());
		
		if(allCaps == 1 || allCaps == true){
				if(x==0) {
					capText = wordArray[x]+" ";
				}else if(x != numWords -1){
					capText = capText+wordArray[x]+" ";
				}else if(x == numWords -1){
					capText = capText+wordArray[x];
				}
		}else{
				if(x==0) {
					capText = wordArray[x]+"";
				}else if(x != numWords -1){
					capText = capText+wordArray[x]+"";
				}else if(x == numWords -1){
					capText = capText+wordArray[x];
				}
		
		}
		
		
	}

	if (allCaps == 1 || allCaps == true) {

		capText = capText.replace(/\.\s\[-<br>-\]\s/g, "\n");
		capText = capText.replace(/\.\s\[-<br>-\]/g, "\n");

	} else {
		capText = capText.replace(/\[-<br>-\]\.\s/g, "\n");
	}

	capText = capText.replace(/\si\s/g, " I ");

	m.value = capText;*/
	
	if(document.getElementById(m.id) != null && document.getElementById(m.id).value != ''){
          document.getElementById(m.id)[document.getElementById(m.id).selectedIndex].innerHTML = capText; 
          
    }   
	
}
