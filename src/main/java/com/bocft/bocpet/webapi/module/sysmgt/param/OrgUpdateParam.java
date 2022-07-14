package com.bocft.bocpet.webapi.module.sysmgt.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-13
 */
@Data
public class OrgUpdateParam implements Serializable {
    @NotNull
    private String orgId;

    private String orgName;

    private String parentId;

    private String regionCode;

    private String orgAddr;

    private String orgContacts;

    private String orgContactDetail;

    private String orgSecretKey;

    private Long picid;

    private String bankFlag;
}
