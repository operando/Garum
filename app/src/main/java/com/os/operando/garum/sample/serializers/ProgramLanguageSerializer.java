package com.os.operando.garum.sample.serializers;

import com.os.operando.garum.sample.enums.ProgramLanguage;
import com.os.operando.garum.serializers.TypeSerializer;

public class ProgramLanguageSerializer extends TypeSerializer<ProgramLanguage, Integer> {

    @Override
    public Class<ProgramLanguage> getDeserializedType() {
        return ProgramLanguage.class;
    }

    @Override
    public Class<Integer> getSerializedType() {
        return int.class;
    }

    @Override
    public Integer serialize(ProgramLanguage data) {
        return data == null ? null : data.getId();
    }

    @Override
    public ProgramLanguage deserialize(Integer data) {
        return ProgramLanguage.valueOf(data);
    }
}