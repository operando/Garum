package com.os.operando.garum;

import android.content.Context;

import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.serializers.TypeSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuration {

    private Context context;
    private List<Class<? extends PrefModel>> modelClasses;
    private List<Class<? extends TypeSerializer>> typeSerializers;

    private Configuration(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public List<Class<? extends PrefModel>> getModelClasses() {
        return modelClasses;
    }

    public List<Class<? extends TypeSerializer>> getTypeSerializers() {
        return typeSerializers;
    }

    public boolean isValid() {
        return modelClasses != null && modelClasses.size() > 0;
    }

    public static class Builder {

        private Context context;
        private List<Class<? extends PrefModel>> modelClasses = new ArrayList<>();
        private List<Class<? extends TypeSerializer>> typeSerializers;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        public Builder addModelClass(Class<? extends PrefModel> modelClass) {
            modelClasses.add(modelClass);
            return this;
        }

        @SafeVarargs
        public final Builder addModelClasses(Class<? extends PrefModel>... modelClasses) {
            this.modelClasses.addAll(Arrays.asList(modelClasses));
            return this;
        }

        @SafeVarargs
        public final Builder setModelClasses(Class<? extends PrefModel>... modelClasses) {
            this.modelClasses = Arrays.asList(modelClasses);
            return this;
        }

        public Builder addTypeSerializer(Class<? extends TypeSerializer> typeSerializer) {
            if (typeSerializers == null) {
                typeSerializers = new ArrayList<>();
            }
            typeSerializers.add(typeSerializer);
            return this;
        }

        public Builder addTypeSerializers(Class<? extends TypeSerializer>... typeSerializers) {
            if (this.typeSerializers == null) {
                this.typeSerializers = new ArrayList<>();
            }
            this.typeSerializers.addAll(Arrays.asList(typeSerializers));
            return this;
        }

        public Builder setTypeSerializers(Class<? extends TypeSerializer>... typeSerializers) {
            this.typeSerializers = Arrays.asList(typeSerializers);
            return this;
        }

        public Configuration create() {
            Configuration configuration = new Configuration(context);
            // todo Get model classes from meta-data
            if (modelClasses != null) {
                configuration.modelClasses = modelClasses;
            }
            // todo Get serializer classes from meta-data
            if (typeSerializers != null) {
                configuration.typeSerializers = typeSerializers;
            }
            return configuration;
        }
    }
}