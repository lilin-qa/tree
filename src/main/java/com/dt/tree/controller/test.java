package com.dt.tree.controller;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
public class test {

    public static  void main(String[] args){

     test test=new test();
//        int[] num={0,-2,1,1,2,0,3,9};
//        System.out.println(test.getSum(num));
    String a="Hello Word!";

  //  System.out.println(a);
       System.out.println(test.isMatch("aaa","*"));

    }

    /**
     * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组
     * （1）首先对数组进行排序（从小到大）
     *  * （2）依次取出第 i 个数（i从0开始），并且不重复的选取（跳过重复的数）
     *  * （3）这样问题就转换为 2 个数求和的问题（可以用双指针解决方法）
     *  *  2 数求和问题
     *  *     （4）定义两个指针：左指针（left） 和 右指针（right）
     *  *     （5）找出固定 left， 此时left所指的位置为数组中最小数，再找到两个数和 不大于 target 的最大 right 的位置
     *  *     （6）调整 left 的位置（后移），求解和是否为 target O(n)
     *  *     （7）时间复杂度：O(nlogn) + O(n*n)

     */
    public ArrayList<ArrayList<Integer>>  threeSum(int[] nums) {
        //int[] num={0,-2,1,1,2};ss   -2 0 1 1 2
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>>  result = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> res;
        for (int i = 0; i < nums.length -2; i++) {
            if (nums[i] >  0) break;

            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                if (nums[low] + nums[high] + nums[i] == 0) {
                    if (!hashSet.contains(Arrays.asList(nums[i], nums[low], nums[high]))) {
                        hashSet.addAll(Arrays.asList(nums[i],nums[low],nums[high]));
                        res=new ArrayList<>();
                        res.add(nums[i]);
                        res.add(nums[low]);
                        res.add(nums[high]);
                        result.add(res);
                    }


                    low++;
                    high--;
                } else if (nums[low] + nums[high] + nums[i] > 0 ) {
                    high--;
                } else {
                    low++;
                }

            }
        }

        return result;
    }



  public  ArrayList<ArrayList<Integer>> getSum(int[] nu){
      if (nu.length<0){
       return  null;
      }
        Arrays.sort(nu);
       ArrayList<ArrayList<Integer>> restult=new ArrayList<>();
        HashSet<Integer> hashSet=new HashSet<>();
        ArrayList<Integer> arrayList=null;

        for (int i=0;i<nu.length;i++){
            if(nu[i]>0){
                break;
            }
            int left=i+1;
            int right=nu.length-1;

            while (left<right){
                if(  nu[i]+nu[left]+nu[right]==0){
                       if(!hashSet.contains(Arrays.asList(nu[i],nu[left],nu[right]))){
                           hashSet.addAll(Arrays.asList(nu[i],nu[left],nu[right]));
                           arrayList=new ArrayList<>();
                           arrayList.add(nu[i]);
                           arrayList.add(nu[left]);
                           arrayList.add(nu[right]);
                           restult.add(arrayList);

                       }
                       left++;
                       right--;

                }else if(left<right && nu[i]+nu[left]+nu[right]>0){
                        right--;
                }else{
                        left++;
                }



            }



        }

        return  restult;

  }

    /**
     * 通配符模式匹配
     * ? :匹配任意一个字符
     * *：匹配任意字符
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "d*a*b") → false
     *用sp和pp分别纪录s和p当前要进行匹配的位置；
     * match纪录s中匹配到的位置，用于让sp下次从这里开始；star纪录*出现的位置，pp从*的下一个位置出发。
     */
    public boolean isMatch(String s, String p) {
        int sp = 0;
        int pp = 0;
        int star = -1;
        int match = 0;
        while(sp < s.length()){
            if(pp < p.length() &&(s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if(pp < p.length() && p.charAt(pp) == '*'){
                star = pp;
                match = sp;
                pp++;
            }else if(star != -1){
                match++;
                sp = match;
                pp = star + 1;
            }else return false;
        }
        while(pp < p.length() && p.charAt(pp) == '*'){
            pp++;
        }
        return p.length() == pp;
    }

}
