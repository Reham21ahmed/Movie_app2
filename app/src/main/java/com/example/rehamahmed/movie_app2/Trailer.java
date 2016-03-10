package com.example.rehamahmed.movie_app2;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;



public class Trailer implements Serializable {
    private Integer id;
    private List<ResultsEntity> results = new ArrayList<ResultsEntity>();

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }


    public class ResultsEntity {
        private String id;
        private String iso6391;
        private String key;
        private String name;
        private String site;
        private Integer size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }


        public String getKey() {
            return key;
        }


        public void setKey(String key) {
            this.key = key;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getSite() {
            return site;
        }


        public void setSite(String site) {
            this.site = site;
        }

        public Integer getSize() {
            return size;
        }


        public void setSize(Integer size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }


        public void setType(String type) {
            this.type = type;
        }

    }

}