/* Generated By:JavaCC: Do not edit this line. Token.java Version 3.0 */
package uk.ac.ucl.panda.utility.parser;

/**
 * Describes the input token stream.
 */

public class Token implements Cloneable{

  /**
   * An integer that describes the kind of this token.  This numbering
   * system is determined by JavaCCParser, and a table of these numbers is
   * stored in the file ...Constants.java.
   */
  public int kind;

  /**
   * beginLine and beginColumn describe the position of the first character
   * of this token; endLine and endColumn describe the position of the
   * last character of this token.
   */
  public int beginLine, beginColumn, endLine, endColumn;

  /**
   * The string image of the token.
   */
  public String image;

  /**
   * A reference to the next regular (non-special) token from the input
   * stream.  If this is the last token from the input stream, or if the
   * token manager has not read tokens beyond this one, this field is
   * set to null.  This is true only if this token is also a regular
   * token.  Otherwise, see below for a description of the contents of
   * this field.
   */
  public Token next;

  /**
   * This field is used to access special tokens that occur prior to this
   * token, but after the immediately preceding regular (non-special) token.
   * If there are no such special tokens, this field is set to null.
   * When there are more than one such special token, this field refers
   * to the last of these special tokens, which in turn refers to the next
   * previous special token through its specialToken field, and so on
   * until the first special token (whose specialToken field is null).
   * The next fields of special tokens refer to other special tokens that
   * immediately follow it (without an intervening regular token).  If there
   * is no such token, this field is null.
   */
  public Token specialToken;

   public static final String DEFAULT_TYPE = "word";
  private static int MIN_BUFFER_SIZE = 10;

  /** @deprecated: we will remove this when we remove the
   * deprecated APIs */
  private String termText;

  char[] termBuffer;                              // characters for the term text
  public int termLength;                                 // length of term text in buffer

  public int startOffset;				  // start in source text
  public int endOffset;				  // end in source text
  String type = DEFAULT_TYPE;                     // lexical type
  
  Payload payload;
  
  int positionIncrement = 1;

  /** Constructs a Token will null text. */
  public Token() {
  }

  
public void setTerm(String newstr){
    termText= newstr;

}


  /**
   * Returns a new Token object, by default. However, if you want, you
   * can create and return subclass objects based on the value of ofKind.
   * Simply add the cases to the switch for all those special cases.
   * For example, if you have a subclass of Token called IDToken that
   * you want to create if ofKind is ID, simlpy add something like :
   *
   *    case MyParserConstants.ID : return new IDToken();
   *
   * to the following switch statement. Then you can cast matchedToken
   * variable to the appropriate type and use it in your lexical actions.
   */
  public static final Token newToken(int ofKind)
  {
     switch(ofKind)
     {
       default : return new Token();
     }
  }
  
  
  /** Constructs a Token with null text and start & end
   *  offsets.
   *  @param start start offset
   *  @param end end offset */
  public Token(int start, int end) {
    startOffset = start;
    endOffset = end;
  }

  /** Constructs a Token with null text and start & end
   *  offsets plus the Token type.
   *  @param start start offset
   *  @param end end offset */
  public Token(int start, int end, String typ) {
    startOffset = start;
    endOffset = end;
    type = typ;
  }

  /** Constructs a Token with the given term text, and start
   *  & end offsets.  The type defaults to "word."
   *  <b>NOTE:</b> for better indexing speed you should
   *  instead use the char[] termBuffer methods to set the
   *  term text.
   *  @param text term text
   *  @param start start offset
   *  @param end end offset */
  public Token(String text, int start, int end) {
    termText = text;
    startOffset = start;
    endOffset = end;
  }

  /** Constructs a Token with the given text, start and end
   *  offsets, & type.  <b>NOTE:</b> for better indexing
   *  speed you should instead use the char[] termBuffer
   *  methods to set the term text.
   *  @param text term text
   *  @param start start offset
   *  @param end end offset
   *  @param typ token type */
  public Token(String text, int start, int end, String typ) {
    termText = text;
    startOffset = start;
    endOffset = end;
    type = typ;
  }

  /** Set the position increment.  This determines the position of this token
   * relative to the previous Token in a {@link TokenStream}, used in phrase
   * searching.
   *
   * <p>The default value is one.
   *
   * <p>Some common uses for this are:<ul>
   *
   * <li>Set it to zero to put multiple terms in the same position.  This is
   * useful if, e.g., a word has multiple stems.  Searches for phrases
   * including either stem will match.  In this case, all but the first stem's
   * increment should be set to zero: the increment of the first instance
   * should be one.  Repeating a token with an increment of zero can also be
   * used to boost the scores of matches on that token.
   *
   * <li>Set it to values greater than one to inhibit exact phrase matches.
   * If, for example, one does not want phrases to match across removed stop
   * words, then one could build a stop word filter that removes stop words and
   * also sets the increment to the number of stop words removed before each
   * non-stop word.  Then exact phrase queries will only match when the terms
   * occur with no intervening stop words.
   *
   * </ul>
   * @see org.apache.lucene.index.TermPositions
   */
  public void setPositionIncrement(int positionIncrement) {
    if (positionIncrement < 0)
      throw new IllegalArgumentException
        ("Increment must be zero or greater: " + positionIncrement);
    this.positionIncrement = positionIncrement;
  }

  /** Returns the position increment of this Token.
   * @see #setPositionIncrement
   */
  public int getPositionIncrement() {
    return positionIncrement;
  }

  /** Sets the Token's term text.  <b>NOTE:</b> for better
   *  indexing speed you should instead use the char[]
   *  termBuffer methods to set the term text. */
  public void setTermText(String text) {
    termText = text;
    termBuffer = null;
  }

  /** Returns the Token's term text.
   * 
   * @deprecated Use {@link #termBuffer()} and {@link
   * #termLength()} instead. */
  public final String termText() {
    if (termText == null && termBuffer != null)
      termText = new String(termBuffer, 0, termLength);
    return termText;
  }

  /** Copies the contents of buffer, starting at offset for
   *  length characters, into the termBuffer
   *  array. <b>NOTE:</b> for better indexing speed you
   *  should instead retrieve the termBuffer, using {@link
   *  #termBuffer()} or {@link #resizeTermBuffer(int)}, and
   *  fill it in directly to set the term text.  This saves
   *  an extra copy. */
  public final void setTermBuffer(char[] buffer, int offset, int length) {
    resizeTermBuffer(length);
    System.arraycopy(buffer, offset, termBuffer, 0, length);
    termLength = length;
  }

  /** Returns the internal termBuffer character array which
   *  you can then directly alter.  If the array is too
   *  small for your token, use {@link
   *  #resizeTermBuffer(int)} to increase it.  After
   *  altering the buffer be sure to call {@link
   *  #setTermLength} to record the number of valid
   *  characters that were placed into the termBuffer. */
  public final char[] termBuffer() {
    initTermBuffer();
    return termBuffer;
  }

  /** Grows the termBuffer to at least size newSize.
   *  @param newSize minimum size of the new termBuffer
   *  @return newly created termBuffer with length >= newSize
   */
  public char[] resizeTermBuffer(int newSize) {
    initTermBuffer();
    if (newSize > termBuffer.length) {
      int size = termBuffer.length;
      while(size < newSize)
        size *= 2;
      char[] newBuffer = new char[size];
      System.arraycopy(termBuffer, 0, newBuffer, 0, termBuffer.length);
      termBuffer = newBuffer;
    }
    return termBuffer;
  }

  // TODO: once we remove the deprecated termText() method
  // and switch entirely to char[] termBuffer we don't need
  // to use this method anymore
  private void initTermBuffer() {
    if (termBuffer == null) {
      if (termText == null) {
        termBuffer = new char[MIN_BUFFER_SIZE];
        termLength = 0;
      } else {
        int length = termText.length();
        if (length < MIN_BUFFER_SIZE) length = MIN_BUFFER_SIZE;
        termBuffer = new char[length];
        termLength = termText.length();
        termText.getChars(0, termText.length(), termBuffer, 0);
        termText = null;
      }
    } else if (termText != null)
      termText = null;
  }

  /** Return number of valid characters (length of the term)
   *  in the termBuffer array. */
  public final int termLength() {
    initTermBuffer();
    return termLength;
  }

  /** Set number of valid characters (length of the term) in
   *  the termBuffer array. */
  public final void setTermLength(int length) {
    initTermBuffer();
    termLength = length;
  }

  /** Returns this Token's starting offset, the position of the first character
    corresponding to this token in the source text.

    Note that the difference between endOffset() and startOffset() may not be
    equal to termText.length(), as the term text may have been altered by a
    stemmer or some other filter. */
  public final int startOffset() {
    return startOffset;
  }

  /** Set the starting offset.
      @see #startOffset() */
  public void setStartOffset(int offset) {
    this.startOffset = offset;
  }

  /** Returns this Token's ending offset, one greater than the position of the
    last character corresponding to this token in the source text. */
  public final int endOffset() {
    return endOffset;
  }

  /** Set the ending offset.
      @see #endOffset() */
  public void setEndOffset(int offset) {
    this.endOffset = offset;
  }

  /** Returns this Token's lexical type.  Defaults to "word". */
  public final String type() {
    return type;
  }

  /** Set the lexical type.
      @see #type() */
  public final void setType(String type) {
    this.type = type;
  }

  /** 
   * Returns this Token's payload.
   */ 
  public Payload getPayload() {
    return this.payload;
  }

  /** 
   * Sets this Token's payload.
   */
  public void setPayload(Payload payload) {
    this.payload = payload;
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append('(');
    initTermBuffer();
    if (termBuffer == null)
      sb.append("null");
    else
      sb.append(termBuffer, 0, termLength);
      sb.append(',').append(startOffset).append(',').append(endOffset);
    if (!type.equals("word"))
      sb.append(",type=").append(type);
    if (positionIncrement != 1)
      sb.append(",posIncr=").append(positionIncrement);
    sb.append(')');
    return sb.toString();
  }

  /** Resets the term text, payload, and positionIncrement to default.
   * Other fields such as startOffset, endOffset and the token type are
   * not reset since they are normally overwritten by the tokenizer. */
  public void clear() {
    payload = null;
    // Leave termBuffer to allow re-use
    termLength = 0;
    termText = null;
    positionIncrement = 1;
    // startOffset = endOffset = 0;
    // type = DEFAULT_TYPE;
  }

  public Object clone() {
    try {
      Token t = (Token)super.clone();
      if (termBuffer != null) {
        t.termBuffer = null;
        t.setTermBuffer(termBuffer, 0, termLength);
      }
      if (payload != null) {
        t.setPayload((Payload) payload.clone());
      }
      return t;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);  // shouldn't happen
    }
  }
  
  
  

}
