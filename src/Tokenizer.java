import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer{  /*
 * This is the class that will identify the keys and assign them to their respective category
 * More on tokens and pattern recognition https://www.w3schools.com/java/java_regex.asp */

    List<Map.Entry<String, String>> tokenizeMap(String text){
        List<Map.Entry<String, String>> tokens = new ArrayList<>();
        Pattern p = Pattern.compile("/(\\w)\\s+(.*?)\\s+//\\1", Pattern.CASE_INSENSITIVE);
        Matcher m =p.matcher(text);

        while (m.find()){
            tokens.add(new AbstractMap.SimpleEntry<>(m.group(1), m.group(2)));
        }

        return tokens;
    }

    static class markUpNode{
        private final String tag;
        private StringBuilder content = new StringBuilder();
        private List<markUpNode> children = new ArrayList<>();

        markUpNode(String tag){
            this.tag = tag;
        }
        public void addChild(markUpNode c){
            children.add(c);
        }
        public void appendContent(String c){
            content.append(c);
        }
    }

    public static markUpNode tokenizerNode(String in){
        markUpNode root = new markUpNode("root");
        Stack<markUpNode> stack = new Stack<>();
        stack.push(root);
        String[] parts = in.split("(?=\\/\\/?\\w)");

        for(String part:parts){
            if(part.startsWith("/")){
                String tag = part.substring(1,2); // gets the single character after "/"
                markUpNode newM = new markUpNode(tag);
                stack.peek().addChild(newM);
                stack.push(newM); //Adding new node with the related tag
                if(part.length() > 2){
                    newM.appendContent(part.substring(2).trim());
                    newM.appendContent(" ");
                }
            }else if(part.startsWith("//")){
                String tag = part.substring(2,3); // Retrieving tag after "//"
                if(stack.peek().tag.equals(tag)){
                    markUpNode finished = stack.pop();
                }
            }else{
                stack.peek().appendContent(part);
                stack.peek() .appendContent(" ");
            }
        }

        return root;
    }
}