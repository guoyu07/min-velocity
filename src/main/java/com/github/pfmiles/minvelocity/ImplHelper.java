package com.github.pfmiles.minvelocity;

import java.util.ArrayList;

/**
 * @author pf-miles
 * 
 */
public class ImplHelper {

    // 根据相对路径(name)和当前路径(curPath, 绝对路径形式)，算出name对应的绝对路径
    public static String resolveAbsName(String name, String curPath) {
        ArrayList<String> relPath = toPathList(name);
        ArrayList<String> base = toPathList(curPath);
        base.remove(base.size() - 1);// to dir path
        for (String p : relPath) {
            if (".".equals(p)) {
                continue;
            } else if ("..".equals(p)) {
                base.remove(base.size() - 1);
            } else {
                base.add(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String b : base) {
            sb.append("/").append(b);
        }
        return sb.toString();
    }

    public static ArrayList<String> toPathList(String path) {
        String[] ss = path.split("/");
        ArrayList<String> ret = new ArrayList<String>();
        for (String s : ss) {
            if (s != null && !"".equals(s))
                ret.add(s);
        }
        return ret;
    }

    /**
     * 取得目前可用的classloader，用于动态加载资源
     */
    public static ClassLoader getCurrentClsLoader() {
        ClassLoader ctxLoader = Thread.currentThread().getContextClassLoader();
        if (ctxLoader != null) {
            try {
                ctxLoader.loadClass(ImplHelper.class.getName());
                return ctxLoader;
            } catch (ClassNotFoundException e) {
            }
        }
        return ImplHelper.class.getClassLoader();
    }
}
