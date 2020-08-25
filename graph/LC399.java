package graph;

import java.util.*;

/**
 * created by mercury on 2020-08-25
 *
 * 除法求值
 *
 * 给出方程式 A / B = k, 其中 A 和 B 均为用字符串表示的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。
 * 如果结果不存在，则返回 -1.0。
 *
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 
 * 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 
 * 返回vector<double>类型。
 *
 * 基于上述例子，输入如下：
 *
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 *
 */

public class LC399 {

    public static double[] calcEquation(List<List<String>> equations, double[] values,
                                        List<List<String>> queries) {
        //图的链式表示
        Map<String, List<String>> pairs = new HashMap<>();
        //图上每条边的权重
        Map<String, List<Double>> valuedPairs = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            //获取第i个方程式
            List<String> equation = equations.get(i);
            String multiplied = equation.get(0);    //被除数
            String multiplier = equation.get(1);    //除数
            //如果被除数从来没有添加到图中，将其作为顶点在图中初始化
            if (!pairs.containsKey(multiplied)) {
                pairs.put(multiplied, new ArrayList<>());
                valuedPairs.put(multiplied, new ArrayList<>());
            }

            //如果除数从来没有添加到图中，将其作为顶点在图中初始化
            if (!pairs.containsKey(multiplier)) {
                pairs.put(multiplier, new ArrayList<>());
                valuedPairs.put(multiplier, new ArrayList<>());
            }
            //添加边和边的权重
            pairs.get(multiplied).add(multiplier);
            pairs.get(multiplier).add(multiplied);
            valuedPairs.get(multiplied).add(values[i]);
            valuedPairs.get(multiplier).add(1 / values[i]);

        }

        //结果集
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            //在图中，以被除数作为顶点，深度优先遍历图，直到找到值为除数的顶点
            result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), pairs, valuedPairs, new HashSet<>(), 1.0);
            result[i] = result[i] == 0 ? -1.0 : result[i];
        }
        
        return result;
    }


    private static double dfs(String multiplied, String multiplier, Map<String, List<String>> pairs,
                              Map<String, List<Double>> valuedPairs, Set<String> visited, double curResult) {
        //如果图中不包含该被除数顶点，则无法获知表达式的值
        if (!pairs.containsKey(multiplied)) {
            return 0;
        }
        //如果再次访问该被除数，说明找到一条环路，dfs结果失败
        if (visited.contains(multiplied)) {
            return 0;
        }
        //如果被除数等于除数，返回1.0
        if (multiplied.equals(multiplier)) {
            return curResult;
        }
        visited.add(multiplied);

        //获得当前被除数的所有邻接顶点
        List<String> multipliers = pairs.get(multiplied);
        //获得所有邻接边的权重
        List<Double> multiplierValues = valuedPairs.get(multiplied);
        double temp = 0;
        for (int i = 0; i < multipliers.size(); i++) {
            //以邻接点为新的顶点，继续dfs
            //此时curResult的值代表该原邻接点除以邻接点的值
            //如a/b=2,b/c=3,则a=2b,因此当以b作为邻接点寻找c时，需要记录原被除数是现被除数的两倍
            temp = dfs(multipliers.get(i), multiplier, pairs, valuedPairs, visited,
                    curResult * multiplierValues.get(i));
            //找到非0路径，结束
            if (temp != 0) {
                break;
            }
        }
        visited.remove(multiplied);

        return temp;
    }


    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>(Arrays.asList("a", "b")));
        equations.add(new ArrayList<>(Arrays.asList("b", "c")));

        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(Arrays.asList("a", "c")));
        queries.add(new ArrayList<>(Arrays.asList("b", "a")));
        queries.add(new ArrayList<>(Arrays.asList("a", "e")));
        queries.add(new ArrayList<>(Arrays.asList("a", "a")));
        queries.add(new ArrayList<>(Arrays.asList("x", "x")));

        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

}
