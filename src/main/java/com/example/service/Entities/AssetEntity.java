package com.example.service.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@Accessors(prefix = "_")
@Serialization
public class AssetEntity {

    @JsonProperty("customerId")
    private UUID _customerId;

    @JsonProperty("assetId")
    private UUID _assetId;

    @JsonProperty("type")
    private String _type;

    @JsonProperty("label")
    private String _label;

    @JsonProperty("eaps")
    private String _eaps;

    @JsonProperty("ipAddresses")
    private Set<String> _ipAddresses;

    @JsonProperty("macAddresses")
    private Set<String> _macAddresses;

    @JsonProperty("createdAt")
    private Date _createdAt;

    @JsonProperty("osVersion")
    private String _osVersion;

    @JsonProperty("platform")
    private String _platform;

    @Override
    public String toString() {
        return "AssetEntity{" +
                "_customerId=" + _customerId +
                ", _assetId=" + _assetId +
                ", _type='" + _type + '\'' +
                ", _label='" + _label + '\'' +
                ", _eaps=" + _eaps +
                ", _ipAddresses=" + _ipAddresses +
                ", _macAddresses=" + _macAddresses +
                ", _createdAt=" + _createdAt +
                ", _osVersion='" + _osVersion + '\'' +
                ", _platform='" + _platform + '\'' +
                '}';
    }
}
