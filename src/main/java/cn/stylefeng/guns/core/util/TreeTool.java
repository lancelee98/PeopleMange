package cn.stylefeng.guns.core.util;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeTool {
    public static List<Integer> removeTreeNode(List<Map<String, Object>> deviceList, Integer deviceId){
        List<Integer> returnList=new ArrayList<>();
        Map<Integer,Integer> t=new HashMap<Integer, Integer>();;
        for(Map<String,Object> map :deviceList)
        {
            t.put(Integer.valueOf(map.get("room_id").toString()), Integer.valueOf(map.get("parent_id").toString()));
        }
        Set<Integer> keys=t.keySet();
        doRemove(t,deviceId,returnList);
        t.remove(deviceId);
        returnList.add(deviceId);
        return returnList;
    }

    //递归删除所有的子节点
    public static Map<Integer, Integer> doRemove(Map<Integer, Integer> t, Integer k, List<Integer> returnList){
        //所有需要删除的子节点
        List<Integer> sons=new ArrayList<Integer>();
        sons.add(k);
        List<Integer> temp=new ArrayList<Integer>();
        //循环递归删除，所有以k为父节点的节点
        while(true){
            for(Integer s:sons){
                Set<Integer> keys=t.keySet();
                Iterator<Integer> it=keys.iterator();
                while(it.hasNext()){
                    Integer n=it.next();
                    //如果父节点（即Map的value）为需要删除的节点，则记录此节点，并在Map中删除
                    if(t.get(n)==s){
                        temp.add(n);
                        it.remove();
                        returnList.add(n);
                    }
                }
            }
            //如果此节点包含子节点，则将子节点赋值给sons;否则表示所有子节点已经删除，结束循环
            if(temp.size()>0){
                sons=temp;
                temp=new CopyOnWriteArrayList<Integer>();
            }else{
                break;
            }
        }
        return t;
    }
}
