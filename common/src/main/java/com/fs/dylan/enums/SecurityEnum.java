package com.fs.dylan.enums;

/**
 * @author jay.li
 * @Title: SecurityEnum
 * @Package com.fs.ppt.enums
 * @Description:
 * @date 2023/8/16
 */
public enum SecurityEnum {
    /**
     *
     * @Field WEB_LICENCE_ID
     */
    WEB_LICENCE_ID("111293", "yuxuan350007");

    private final String licenceId;
    private final String licenceKey;

    SecurityEnum(String licenseId, String licenseKey) {
        this.licenceId = licenseId;
        this.licenceKey = licenseKey;
    }

    public static String getLicenceKeyById(String licenceId) {
        SecurityEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            SecurityEnum securityEnum = var1[var3];
            if (securityEnum.getLicenceId().equals(licenceId)) {
                return securityEnum.getLicenceKey();
            }
        }

        return null;
    }

    public String getLicenceId() {
        return this.licenceId;
    }

    public String getLicenceKey() {
        return this.licenceKey;
    }
}

