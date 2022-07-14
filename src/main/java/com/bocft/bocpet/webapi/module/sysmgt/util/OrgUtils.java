package com.bocft.bocpet.webapi.module.sysmgt.util;

import com.bocft.bocpet.webapi.module.sysmgt.entity.Org;

import java.util.ArrayList;
import java.util.List;

public class OrgUtils {

    public static List<Org> generateOrgTree(String rootId, List<Org> orgList) {
        List<Org> rootOrgs = new ArrayList<>();
        for (Org org : orgList) {
            if (org.getParentId().equals(rootId)) {
                rootOrgs.add(org);
            }
        }
        for (Org org : rootOrgs) {
            org.getChildren().addAll(generateOrgTree(org.getOrgId(), orgList));
        }
        return rootOrgs;
    }
}
