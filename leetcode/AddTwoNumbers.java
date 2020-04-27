package leetcode;

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;

}

  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

//  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//  int d1=0;
//  int d2=0;
//  int sum = 0;
//  ListNode l3 = new ListNode(0);
//
//  while (l1!=null || l2!=null) {
//
//  	if(l1!=null){
//  		d1 = l1.val;
//  		l1 = l1.next;
//  	}
//  	else
//  		d1=0;
//
//  	if(l2!=null){
//  		d2 = l2.val;
//  		l2=l2.next;
//  	}
//  	else
//  		d2=0;
//
//  sum = d1+d2+l3.val;
//  l3.val = sum%10;
//	ListNode tmp = l3;
//	l3 = new ListNode(sum/10);
//	l3.next = tmp;
//  }
//  return l3;
//}

}



