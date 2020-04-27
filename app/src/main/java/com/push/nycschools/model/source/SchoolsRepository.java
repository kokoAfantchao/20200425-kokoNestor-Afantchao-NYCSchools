package com.push.nycschools.model.source;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class SchoolsRepository implements SchoolDataSource {

    private static SchoolsRepository INSTANCE = null;

    private final SchoolDataSource remoteDataSource ;

    //TODO  have locale dataBase to make data always
    private final SchoolDataSource locaDataSource ;



    private SchoolsRepository(@NotNull SchoolDataSource  remoteSource, @NotNull  SchoolDataSource
                              locaSource ){
        remoteDataSource = remoteSource ;
        locaDataSource = locaSource;

    }


    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param remoteSource the backend data source
     *
     */
    //TODO Add Local dataSource later
    public static SchoolsRepository  getInstance( SchoolDataSource remoteSource ){

        if(INSTANCE == null ){
            INSTANCE = new SchoolsRepository(remoteSource ,  remoteSource );
        }

        return INSTANCE ;
    }


     /**
      * Used to force {@link #getInstance( SchoolDataSource )} to create a new instance
      * next time it's called.
      */
    public static void  destroyInstance(){
        INSTANCE  =  null ;
    }

     //TODO  implement a good logic handle data localy and remotely
     @Override
     public void getSchools(@NotNull final  LoadSchoolCallBack callback ) {


     }
 }
