package cn.util;


import org.aspectj.apache.bcel.classfile.InnerClass;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/6/25
 * Time 11:18
 */
public class Test {
    private final static int _1mb=1024*1024;

    public static void main(String args[]) throws Throwable {
//        ListNode head=new ListNode(1);
//        head.next=new ListNode(3);
//        head.next.next=new ListNode(5);
//          ArrayList<String> list=Solution.Permutation("aac");
//        for (String i:
//             list) {
//            System.out.println(i);
//        }

//        Map<String,Integer> map=new HashMap<>();
//        map.put("第一个",1);
//        map.put("第二个",2);
//        map.put("第三个",3);
//        Iterator<Map.Entry<String,Integer>> iterator=map.entrySet().iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next().getValue());
//        }
//        System.out.println(map.toString());

    }
}

//class ListNode {
//    int val;
//    ListNode next = null;
//
//    ListNode(int val) {
//        this.val = val;
//    }
//}
//class TreeNode {
//    int val = 0;
//    TreeNode left = null;
//    TreeNode right = null;
//
//    public TreeNode(int val) {
//        this.val = val;
//
//    }
//
//}
//class FinalizeGC{
//    public static FinalizeGC SAVE_HOOK=null;
//    public void isAlive(){
//        System.out.println("i am alive");
//    }
//    @Override
//    public void finalize() throws Throwable {
//        super.finalize();
//        System.out.println("finalize executed");
//        FinalizeGC.SAVE_HOOK=this;
//    }
//}
//class Solution {
//    protected static TreeNode leftLast = null;//指向当前左子树转化的链表的最后一位
//    protected static Set<String> strSet;
//    void MultiThread(){
//        ThreadLocal<String> threadLocal=new ThreadLocal<>();
//        Thread thread=new Thread();
//        thread.start();
//    }
//    static TreeNode Convert(TreeNode pRootOfTree) {
//        if (pRootOfTree == null) return null;
//        if (pRootOfTree.left == null && pRootOfTree.right == null) {
//            leftLast=pRootOfTree;
//            return pRootOfTree;//叶子节点直接返回
//        }
//        TreeNode pre=Convert(pRootOfTree.left);//左子树部分转化为链表,会改变leftLast的指向
//        if(pre!=null) {//防止左子树为null
//            leftLast.right = pRootOfTree;
//            pRootOfTree.left = leftLast;
//            leftLast = pRootOfTree;//此时最大的为当前节点
//        }
//        TreeNode after = Convert(pRootOfTree.right);//连接上右边排好序的链表
//        if(after!=null) {
//            after.left = pRootOfTree;
//            pRootOfTree.right = after;
//        }
//
//        return pre!=null?pre:pRootOfTree;//返回左头或者当前节点
//
//    }
//    static void Permutations(String strLeft,String nowStr){
//        if(strLeft.isEmpty() && !nowStr.isEmpty())
//        {
//            strSet.add(nowStr);
//            return;
//        }
//        for(int i=0;i<strLeft.length();i++){
//            Permutations(strLeft.substring(0,i)+strLeft.substring(i+1,strLeft.length()), nowStr+strLeft.charAt(i));
//        }
//    }
//    static ArrayList<Integer> PrintFromTopToBottom(TreeNode root){
//        ArrayList<Integer> list=new ArrayList<>();
//        ArrayList<TreeNode> queue=new ArrayList<TreeNode>();
//        if(root==null) return list;
//        queue.add(root);
//       while (!queue.isEmpty()){
//           TreeNode node=queue.remove(0);
//           list.add(node.val);
//           if(node.left!=null) queue.add(node.left);
//           if(node.right!=null) queue.add(node.right);
//       }
//       Integer a=Integer.valueOf(100);
//
//        return list;
//
//    }
//    static void addToArray(TreeNode root,ArrayList<Integer> list){
//    }
//    static void Traverse(ListNode head){
//        while(head!=null){
//            System.out.println(head.val);
//            head=head.next;
//        }
//    }
//}


