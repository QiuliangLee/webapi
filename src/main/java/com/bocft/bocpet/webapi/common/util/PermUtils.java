package com.bocft.bocpet.webapi.common.util;

import cn.hutool.core.collection.CollectionUtil;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermUtils {

    public static List<Perm> generatePermList(Integer rootId, List<Perm> perms) {
        List<Perm> rootPerms = new ArrayList<>();
        for (Perm perm : perms) {
            if (perm.getParentId().equals(rootId)) {
                rootPerms.add(perm);
            }
        }
        for (Perm perm : rootPerms) {
            perm.getChildren().addAll(generatePermList(perm.getPid(), perms));
        }
        return rootPerms;
    }

    public static List<Perm> findAncestorPerms(List<Perm> allPerms, Set<Integer> descendants) {
        Set<Integer> ancestors = new HashSet<>();
        descendants.forEach(integer -> findAncestors(allPerms, integer, ancestors));
        ancestors.addAll(descendants);
        return allPerms.stream().filter(item -> ancestors.contains(item.getPid())).collect(Collectors.toList());
    }

    private static void findAncestors(List<Perm> allPerms, Integer descendant, Set<Integer> res) {
        List<Perm> descendantPerm = allPerms.stream().filter(item -> item.getPid().equals(descendant)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(descendantPerm)) {
            Integer parentId = descendantPerm.get(0).getParentId();
            res.add(parentId);
            findAncestors(allPerms, parentId, res);
        }
    }
}
