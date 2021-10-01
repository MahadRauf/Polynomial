package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly1 == null && poly2 == null){
			return null;
		}
		Node ptr = null;
		Node ret = null;
		if (poly1 != null){
			if(poly1.term.coeff == 0){
				poly1 = poly1.next;
			}
		}
		if (poly2 != null){
			if(poly2.term.coeff == 0){
				poly2 = poly2.next;
			}
		}
		if(poly1 != null && poly2 != null){
			if (poly1.term.degree == poly2.term.degree){
				float coeff = poly1.term.coeff + poly2.term.coeff;
				int degree = poly1.term.degree;
				if(coeff != 0){
					ret = new Node(coeff, degree, null);
					ptr = ret;
				}
				poly1 = poly1.next;
				poly2 = poly2.next;
			}else if (poly1.term.degree < poly2.term.degree){
				float coeff = poly1.term.coeff;
				int degree = poly1.term.degree;
				if(coeff != 0){
					ret = new Node(coeff, degree, null);
					ptr = ret;
				}
				poly1 = poly1.next;
			}else if(poly1.term.degree > poly2.term.degree){
				float coeff = poly2.term.coeff;
				int degree = poly2.term.degree;
				if(coeff != 0){
					ret = new Node(coeff, degree, null);
					ptr = ret;
				}
				poly2 = poly2.next;
			}
		}
		while(poly1 != null && poly2 != null){
			if(poly1.term.coeff == 0){
				poly1 = poly1.next;
			}
			if(poly2.term.coeff == 0){
				poly2 = poly2.next;
			}
			if(poly1 != null && poly2 != null){
				if (poly1.term.degree == poly2.term.degree){
					float coeffL = poly1.term.coeff + poly2.term.coeff;
					if(coeffL != 0){
						Node temp = new Node(coeffL, poly1.term.degree, null);
						ptr.next = temp;
						ptr = temp;
					}
					poly1 = poly1.next;
					poly2 = poly2.next;
				}else if(poly1.term.degree < poly2.term.degree){
					if(poly1.term.coeff != 0){
						Node temp = new Node(poly1.term.coeff, poly1.term.degree, null);
						ptr.next = temp;
						ptr = temp;
					}
					poly1 = poly1.next;
				}else if(poly1.term.degree > poly2.term.degree){
					if(poly2.term.coeff != 0){
						Node temp = new Node(poly2.term.coeff, poly2.term.degree, null);
						ptr.next = temp;
						ptr = temp;
					}
					poly2 = poly2.next;
				}
			}
		}
		if (ret == null && poly1 == null && poly2 != null){
			ret = new Node(poly2.term.coeff, poly2.term.degree, null);
			ptr = ret;
			poly2 = poly2.next;
		}
		if (ret == null && poly2 == null && poly1 != null){
			ret = new Node(poly1.term.coeff, poly1.term.degree, null);
			ptr = ret;
			poly1 = poly1.next;
		}
		if (poly1 != null){
			while(poly1 != null){
				if(poly1.term.coeff != 0){
					Node temp = new Node(poly1.term.coeff, poly1.term.degree, null);
					ptr.next = temp;
					ptr = temp;
				}
				poly1 = poly1.next;
			}
		}
		if(poly2 != null){
			while(poly2 != null){
				if(poly2.term.coeff != 0){
					Node temp = new Node(poly2.term.coeff, poly2.term.degree, null);
					ptr.next = temp;
					ptr = temp;
				}
				
				poly2 = poly2.next;
			}
		}
		return ret;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		if (poly1 == null || poly2 == null){
			return null;
		}
		//Node ptr = null;
		Node ret = null;
		/*if (poly1 == null){
			ret = new Node(poly2.term.coeff, poly2.term.degree, null);
			ptr = ret;
			poly2 = poly2.next;
			while(poly2 != null){
				ptr.next = new Node(poly2.term.coeff, poly2.term.degree, null);
				ptr = ptr.next;
				poly2 = poly2.next;
			}
			return ret;
		}
		if (poly2 == null){
			ret = new Node(poly1.term.coeff, poly1.term.degree, null);
			ptr = ret;
			poly1 = poly1.next;
			while(poly1 != null){
				ptr.next = new Node(poly1.term.coeff, poly1.term.degree, null);
				ptr = ptr.next;
				poly1 = poly1.next;
			}
			return ret;
		}*/
		ret = new Node((poly1.term.coeff*poly2.term.coeff), (poly1.term.degree+poly2.term.degree), null);
		Node ptrR = ret;
		Node ptrP1 = poly1;
		Node ptrP2 = poly2.next;
		while(ptrP1 != null){
			while(ptrP2 != null){
				ptrR.next = new Node((ptrP1.term.coeff*ptrP2.term.coeff), (ptrP1.term.degree+ptrP2.term.degree), null);
				ptrR = ptrR.next;
				ptrP2 = ptrP2.next;
			}
			ptrP2 = poly2;
			ptrP1 = ptrP1.next;

		}
		ptrR = ret;
		while(ptrR != null){
			Node temp = ptrR.next;
			while (temp != null){
				if (ptrR.term.degree == temp.term.degree){
					ptrR.term.coeff = ptrR.term.coeff + temp.term.coeff;
					temp.term.coeff = 0;
				}
				temp = temp.next;
			}
			ptrR = ptrR.next;
		}
		ptrR = ret;
		Node removeZ = null;
		while(ptrR != null){
			if(ptrR.term.coeff != 0){
				removeZ = new Node(ptrR.term.coeff, ptrR.term.degree, null);
				break;
			}
			ptrR = ptrR.next;
		}
		Node ptrRZ = removeZ;
		ptrR = ret.next;
		while(ptrR != null){
			if(ptrR.term.coeff != 0){
				ptrRZ.next = new Node(ptrR.term.coeff, ptrR.term.degree, null);
				ptrRZ = ptrRZ.next;
			}
			ptrR = ptrR.next;
		}
		ptrRZ = removeZ;
		while(ptrRZ != null){
			Node temp = ptrRZ.next;
			while(temp != null){
				if (temp.term.degree < ptrRZ.term.degree){
					float tempf = ptrRZ.term.coeff;
					int tempi = ptrRZ.term.degree;
					ptrRZ.term.coeff = temp.term.coeff;
					ptrRZ.term.degree = temp. term.degree;
					temp.term.coeff = tempf;
					temp.term.degree = tempi;
				}
				temp = temp.next;
			}
			ptrRZ = ptrRZ.next;
		}
		return removeZ;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if (poly == null){
			return 0;
		}
		float ret = 0;
        Node temp = poly;
        while(temp != null){
            int pow = temp.term.degree;
            float coeff = temp.term.coeff;
            ret += coeff*(Math.pow(x, pow));
            temp = temp.next;
        }
        return ret;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
	/* from old polynomial idk if itll help, prob not
		just dropped it here cause why not
	private static Node reverse(Node p) {
		Node ret=null;
		while (p != null) {
			ret = new Node(p.term.coeff, p.term.degree, ret);
			p = p.next;
		}
		return ret;
	}
	*/
}
