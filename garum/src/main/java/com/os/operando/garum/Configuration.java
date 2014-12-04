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
    private int cacheSize;


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

    public int getCacheSize() {
        return cacheSize;
    }

    public boolean isValid() {
        return modelClasses != null && modelClasses.size() > 0;
    }

    public static class Builder {

        private static final int DEFAULT_CACHE_SIZE = 1024;

        private Context context;

        private Integer cacheSize;
        private List<Class<? extends PrefModel>> modelClasses = new ArrayList<Class<? extends PrefModel>>();
        private List<Class<? extends TypeSerializer>> typeSerializers;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
            cacheSize = DEFAULT_CACHE_SIZE;
        }

        //////////////////////////////////////////////////////////////////////////////////////
        // PUBLIC METHODS
        //////////////////////////////////////////////////////////////////////////////////////

        public Builder setCacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder addModelClass(Class<? extends PrefModel> modelClass) {
            modelClasses.add(modelClass);
            return this;
        }

        public Builder addModelClasses(Class<? extends PrefModel>... modelClasses) {
            this.modelClasses.addAll(Arrays.asList(modelClasses));
            return this;
        }

        public Builder setModelClasses(Class<? extends PrefModel>... modelClasses) {
            this.modelClasses = Arrays.asList(modelClasses);
            return this;
        }

        public Builder addTypeSerializer(Class<? extends TypeSerializer> typeSerializer) {
            if (typeSerializers == null) {
                typeSerializers = new ArrayList<Class<? extends TypeSerializer>>();
            }

            typeSerializers.add(typeSerializer);
            return this;
        }

        public Builder addTypeSerializers(Class<? extends TypeSerializer>... typeSerializers) {
            if (this.typeSerializers == null) {
                this.typeSerializers = new ArrayList<Class<? extends TypeSerializer>>();
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
            configuration.cacheSize = cacheSize;


            // Get model classes from meta-data
            if (modelClasses != null) {
                configuration.modelClasses = modelClasses;
            } else {
//                final String modelList = ReflectionUtil.getMetaData(context, AA_MODELS);
//                if (modelList != null) {
//                    configuration.mModelClasses = loadModelList(modelList.split(","));
//                }
            }

            if (typeSerializers != null) {
                configuration.typeSerializers = typeSerializers;
            } else {
//                final String serializerList = ReflectionUtils.getMetaData(mContext, AA_SERIALIZERS);
//                if (serializerList != null) {
//                    configuration.mTypeSerializers = loadSerializerList(serializerList.split(","));
//                }
            }
            return configuration;
        }
    }
}
