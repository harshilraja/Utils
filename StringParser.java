package ez.eau.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/** 
 * This StrinParser utility class will provide various methods for string manipulations
 *
 * @author Author: 320004070
 * @version Revision: 1.0   
 */
public  class  StringParser {

	 public static final String DEFAULT_ENCODING="8859_1";

	       /** 
	    	* This method inserts the empty string if the value is null else returns the trimmed value. invokes 
	        * @param    String value
	        * @return   String value
	        *  @see     Also see getValidString mehtod
	        */
	    public static String insertSpace(String value){
	    	String valueCopy=value;
	    	if(isEmpty(valueCopy)){
	    		return " ";
	    	}
	    	valueCopy=getValidString(valueCopy);
	    	return valueCopy;
	    }
           /** 
            * Gets the integer value out of the String value supplied. If any exception occurs, 
            * returning the default value supplied as another parameter.  
            * @param    String value, integer to set as default
            * @param    integer to set on exception
            * @return   integer
            */
        public static int getInt(String value,int defaultValue) {
            int returnVal = defaultValue ;
            try{
               returnVal = (int) Double.parseDouble(value);
            } catch(NumberFormatException nfe){
               returnVal = defaultValue ;  
            } catch (NullPointerException npe) {
               returnVal = defaultValue ;  
            }
            return returnVal;
            
        }
      /**
        * Checks if Array contains the given key or not.  
        * @param    string array in which the search happens
        * @param    string value to be searched in the array 
        * @return   boolean value
        */
    public static boolean isInArray(String[] p_arr, String p_str) {
        boolean result = false ;
        if (p_str  != null && p_arr != null) {
        	Arrays.sort(p_arr);
        	result = Arrays.binarySearch(p_arr, p_str.trim()) >=0 ? true : false;        	
        }
        return result ;
    }
	    // Check it
	    public static String getValidString(String string){
	        if(string == null){
	            return "";
	        }
	        else {
	            return string.trim();
	        }
	    }
	    
       /** 
    	* Verifies the String value for having values other than spaces.
    	* null and Strings with only spaces are treated as empty  
        * @param    String to be verified
        * @return   boolean value. false if it contains value.
        */
	    public static boolean isEmpty(String p_str){
            boolean p_empty = true ;
            if (p_str != null && p_str.trim().length() > 0) {
                p_empty = false ;
            }
            return p_empty ;
	    }
	       /** 
    	* Verifies the String value for having values. If spaceOK is false, 
    	* returns same as isEmpty()   
        * @param    String to be verified
        * @param    spaceOK - if spaces are acceptable
        * @return   boolean value. false if it contains value.
        */
	    public static boolean isEmpty(String p_str, boolean spaceOK){
            boolean p_empty = true ;
            if (spaceOK) {
                if (p_str != null && p_str.length() > 0) {
                    p_empty = false ;
                }
            } else {
                if (p_str != null && p_str.trim().length() > 0) {
                    p_empty = false ;
                }
            }
            return p_empty ;
	    }
	    
        /**
         * Determine if the parameter passed is Yes or No
         * The following values will return true irrespective of case
         * Yes, Y, True, T , .T.
         * All other values will return false
         *     
         * @param p_parm
         * @return
         */
        public static boolean checkIfTrue(String p_parm) {

        	String[] truevalues = new String[] {"y","yes","true","t",".t.","1"} ;
            return isInArray(truevalues, p_parm.toLowerCase().trim());
        }
        
        // Returns a String Representation of an Object 
        // If Array, converts Array to a delimited String
           /** 
            * Converts the object into String and appends the character as the separator
            * between the elements.        
            * @param    Object to be converted
            * @param    char as separator 
            * @return   String as converted string
            */
        public static String objectToString(Object o, char p_sep) {
            String res = null ;
            if (o != null) {   
                if (o.getClass().isArray()) {
                    StringBuffer sb = new StringBuffer() ;
                    int arrayLength = Array.getLength(o) ;
                    for (int i=0; i < arrayLength; i++) {
                        sb.append(Array.get(o,i)) .append(p_sep) ;
                    }
                    res = sb.toString() ;
                } else {
                    res = o.toString() ;
                }
            }
            return res ;
        }
        // same as ablove
           /** 
        	* Converts the Array into string of elements separator by ',' 
            * @param    Description of parameters for methods and constructors. Repeat for each parameter
            * @param    Object Array to be converted 
            * @return   String as converted string
            */
        public static String objectToString(Object[] array) {
        	
        	String res = Arrays.asList(array).toString().substring(1,Arrays.asList(array).toString().length()-1);
        	if(res.indexOf(" ")> -1){
        		res = res.replaceAll(" ", "");
        	}
        	return res;
        }        
        
        /**
         * Just a method override so that objectToString can be called on collections
         * 
         * @param p_collection
         * @param p_sep
         * @return
         */
        public static String objectToString(Collection p_collection, char p_sep) {
        	return convertCollectionToString(p_collection, String.valueOf(p_sep)) ;
        }
	    // chek the implementation
	       /** 
            * Converts the Collection into String and appends the character as the separator
            * between the elements.        
            * @param    Collection to be converted
            * @param    char as separator 
            * @return   String as converted string
	        */
	    public static String convertCollectionToString(Collection p_Collection,String p_Separator)
	    {
	        StringBuffer l_String=new StringBuffer();
	        Iterator l_Iterator=p_Collection.iterator();
	        while(l_Iterator.hasNext())
	        {
	            l_String.append(l_Iterator.next().toString()).append(p_Separator);
	        }
	        return l_String.toString();
	        
	    }
        /** Right Justifies integer inum, padding to the left with padchar and returns a string of padlen characters
         *   If padlen is greater than length of inum, first padlen characters from s are returned 
         *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
         *             <code>RightJustify(12000,4,"-")</code returns 1200 
         *   </ul>
         *   @param inum - integer to be padded
         *   @param padlen - desired length of returned string
         *   @param padchar - character to be used for padding
         *   @return String value
         */

         public static String pad(int inum, int p_padlen, String p_padStr,char p_Justify) {
           String p_s = Integer.toString(inum);
           String p_res = pad(p_s,p_padlen,p_padStr,p_Justify) ;
           return p_res ;
         }

         /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
          *   If padlen is greater than length of p_s, first padlen characters from s are returned 
          *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
          *             <code>RightJustify(12000,4,"-")</code returns 1200 
          *   </ul>
          *   @param inum - integer to be padded
          *   @param padlen - desired length of returned string
          *   @param padchar - character to be used for padding
          *   @return String value
          */

         public static String pad(String p_s,int p_padlen, String p_padStr, char p_Justify) {
           String p_res = p_s ;
           if (p_s == null) { p_res = "" ;}  
           
           if (p_padStr != null && p_padStr.length() > 0 ) {
             if (p_s.length() > p_padlen) {
                 p_res = p_s.substring(0,p_padlen) ;
             } else {
                 if (p_padStr.length() == 1) {
                     // Use the more efficient character padding
                     char p_padChar = p_padStr.charAt(0) ;
                     p_res = pad(p_s,p_padlen,p_padChar,p_Justify) ;
                 } else {    
                     StringBuffer sb = new StringBuffer(p_s) ;
                     switch (p_Justify) {
                         case 'R':
                             sb = padLeft(sb,p_padlen,p_padStr) ; break ;
                         case 'L':
                             sb = padRight(sb,p_padlen,p_padStr) ; break ;
                         case 'C':
                             sb = padBoth(sb,p_padlen,p_padStr) ; break ;
                     }                       
                     p_res = sb.toString() ;
                 }    
             }
           }
           return p_res ;
         } 
         /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
          *   If padlen is greater than length of p_s, first padlen characters from s are returned 
          *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
          *             <code>RightJustify(12000,4,"-")</code returns 1200 
          *   </ul>
          *   @param inum - integer to be padded
          *   @param padlen - desired length of returned string
          *   @param padchar - character to be used for padding
          *   @return String value
          */
 
         public static String pad(String p_s,int p_padlen, char p_padchar, char p_Justify) {
        	String p_res = p_s ;
        	if (p_res == null) { p_res = "" ;}  
            if (p_s.length() > p_padlen) {
              p_res = p_s.substring(0,p_padlen) ;
           } else {
              StringBuffer sb = new StringBuffer(p_s) ;
              switch (p_Justify) {
                 case 'R':
                     sb = padLeft(sb,p_padlen,p_padchar) ; break ;
                 case 'L':
                     sb = padRight(sb,p_padlen,p_padchar) ; break ;
                 case 'C':
                     sb = padBoth(sb,p_padlen,p_padchar) ; break ;
              }                       
              p_res = sb.toString() ;
           }
           return p_res ;
         }    
         /** String p_s, padding to the left with padchar and returns a string of padlen characters
          *   If padlen is greater than length of p_s, first padlen characters from s are returned 
          *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
          *             <code>RightJustify(12000,4,"-")</code returns 1200 
          *   </ul>
          *   @param inum - integer to be padded
          *   @param padlen - desired length of returned string
          *   @param padchar - character to be used for padding
          *   @return String value
          */
         private static StringBuffer padLeft(StringBuffer p_s, int p_padLen, String p_padChar) {
             return p_s.insert(0,replicate(p_padChar,p_padLen - p_s.length())) ;
        }
         /** Left Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
          *   If padlen is greater than length of p_s, first padlen characters from s are returned 
          *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
          *             <code>RightJustify(12000,4,"-")</code returns 1200 
          *   </ul>
          *   @param inum - integer to be padded
          *   @param padlen - desired length of returned string
          *   @param padchar - character to be used for padding
          *   @return String value
          */

        private static StringBuffer padLeft(StringBuffer p_s, int p_padLen, char p_padChar) {
             return p_s.insert(0,replicate(p_padChar,p_padLen - p_s.length())) ;
        }
        /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
         *   If padlen is greater than length of p_s, first padlen characters from s are returned 
         *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
         *             <code>RightJustify(12000,4,"-")</code returns 1200 
         *   </ul>
         *   @param inum - integer to be padded
         *   @param padlen - desired length of returned string
         *   @param padchar - character to be used for padding
         *   @return String value
         */

        private static StringBuffer padRight(StringBuffer p_s, int p_padLen, String p_padChar) {
             return p_s.append(replicate(p_padChar,p_padLen - p_s.length())) ;
        }
        /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
         *   If padlen is greater than length of p_s, first padlen characters from s are returned 
         *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
         *             <code>RightJustify(12000,4,"-")</code returns 1200 
         *   </ul>
         *   @param inum - integer to be padded
         *   @param padlen - desired length of returned string
         *   @param padchar - character to be used for padding
         *   @return String value
         */

        private static StringBuffer padRight(StringBuffer p_s, int p_padLen, char p_padChar) {
             return p_s.append(replicate(p_padChar,p_padLen - p_s.length())) ;
        }
        /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
         *   If padlen is greater than length of p_s, first padlen characters from s are returned 
         *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
         *             <code>RightJustify(12000,4,"-")</code returns 1200 
         *   </ul>
         *   @param inum - integer to be padded
         *   @param padlen - desired length of returned string
         *   @param padchar - character to be used for padding
         *   @return String value
         */

        private static StringBuffer padBoth(StringBuffer p_s, int p_padLen, String p_padChar) {
             int midlen = (p_padLen - p_s.length()) / 2 ;
             char[] p_res = replicate(p_padChar,midlen) ;
             p_s.insert(0,p_res)
                .append(p_res) ;
             if (p_s.length() < p_padLen) {
                 return padRight(p_s,p_padLen,p_padChar) ;
             }
             else{ 
                 return p_s ;
             }
        }
        /** Right Justifies String p_s, padding to the left with padchar and returns a string of padlen characters
         *   If padlen is greater than length of p_s, first padlen characters from s are returned 
         *   <ul> e.g. <code>RightJustify(12000,10,"-")</code> returns  -----12000 <br>
         *             <code>RightJustify(12000,4,"-")</code returns 1200 
         *   </ul>
         *   @param inum - integer to be padded
         *   @param padlen - desired length of returned string
         *   @param padchar - character to be used for padding
         *   @return String value
         */

        private static StringBuffer padBoth(StringBuffer p_s, int p_padLen, char p_padChar) {
             int midlen = (p_padLen - p_s.length()) / 2 ;
             char[] p_res = replicate(p_padChar,midlen) ;
             p_s.insert(0,p_res)
                .append(p_res) ;
             return padRight(p_s,p_padLen,p_padChar) ;
        }
        /** Repeats string s to a length of tolen. 
         *  <ul>e.g. <code>replicate("-",10)</code> returns ----------
         *           <code>replicate("ABC",10)</code> returns ABCABCABCAB
         *  </ul>
         *   @param s - string to be repeated
         *   @param tolen - final length of desired string
         */
         private static char[] replicate(String s, int tolen) {
           if (s == null){ 
        	   return null;  
           }
           int arraysize = (int) (tolen / s.length()) ;
           StringBuffer sb = new StringBuffer(s) ;
           for (int j = 1;j < arraysize ;j++) {
                 sb.append(s) ;
           }
           if (tolen > sb.length()) {
               sb.append(s.substring(0,tolen - sb.length())) ;
           }    
           char[] cArray = new char[sb.length()] ;    
           sb.getChars(0, sb.length(), cArray, 0) ;
           return cArray ;
         }
         /** Repeats character s to a length of tolen. This function is more efficient
          * than replicate (String,int)
         *  <ul>e.g. <code>replicate('-',10)</code> returns ----------
         *  </ul>
         *   @param s - string to be repeated
         *   @param tolen - final length of desired string
         */
         private static char[] replicate(char c, int tolen) {
           char[] cArray = new char[tolen] ;  
           Arrays.fill(cArray,c) ;
           return cArray ;
         }
       
         
}

