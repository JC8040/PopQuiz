//////////////////// LinearNode ////////////////////
/**
 * LinearNode represents a node in a linked list.
 * Used from 1027 Assignment.
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */
public class LinearNode<E> {
    
	
	////////// ATTRIBUTES //////////
	/**
	 * The next LinearNode of the current node.
	 */
	private LinearNode<E> next;
	
	/**
	 * The element stored in the current node.
	 */
    private E element;
    
    
    ////////// CONSTRUCTOR //////////
    /**
     * Default constructor initializes a node storing the input element.
     * @param elem 	The element to be stored in the node.
     */
    public LinearNode (E elem) {
        next = null;
        element = elem;
    }
    
    
    ////////// METHODS //////////
    /**
     * Gets the next node in the list.
     * @return next	 The node that follows the current node.
     */
    public LinearNode<E> getNext() {
        return next;
    }
    
    /**
     * Sets the next node in the list.
     * @param node   The node to follow the current node.
     */
    public void setNext (LinearNode<E> node) {
        next = node;
    }
    
    /**
     * Gets the element stored in this node.
     * @return element	The element stored the current node.
     */
    public E getElement() {
        return element;
    }
    
    /**
     * Sets the element stored in this node.
     * @param elem  The element to be stored in the current node.
     */
    public void setElement (E elem) {
        element = elem;
    }
    
} //////////////////// End of LinearNode ////////////////////