package karu1231.testpl.command;

import java.util.ArrayList;
import java.util.List;
public class TabCompleter {
	
	public static List<String> Complete(String now, List<String> collects){
		
		if(now == null || now.isEmpty())
			return collects;
		
		return new ArrayList<String>(){
			{
				for(String collect:collects)
					if(collect.startsWith(now))
						add(collect);
			}
		};
		
	}
	
}
