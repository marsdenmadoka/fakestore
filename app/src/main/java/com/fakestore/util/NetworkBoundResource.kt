package com.fakestore.util
import kotlinx.coroutines.flow.*

/**a helper class/function that coordinates between the db and restApi**/
/**Emits the sealed class Resources instances
//HigherOrderFunction */

inline fun <ResultType, RequestType> networkBoundResource( //building our own flow using flow builder
    crossinline query: () -> Flow<ResultType>,  /**functional argument/parameter responsible for getting data from the local database**/
    crossinline fetch: suspend () -> RequestType, /**fetching new data from the  rest api suspend since our NetRequest is suspend type,return request type**/
    crossinline saveFetchResult: suspend (RequestType) -> Unit, /**get fetched data from fetch function and saved it to our sql lite//takes RequestType as argument**/
    crossinline shouldFetch: (ResultType) -> Boolean = { true },  /**decide if data from cache is still and weather we have to fetch dat from the web or not**/

) = flow {
    /**executed whenever we call the network bound function*/
    val data = query().first()//return list of products

    val flow =
        if (shouldFetch(data)) { //decide if its time to fetch new data or not |return true if default
            emit(Resource.Loading(data))
            try {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) } /**emit a whole stream of data ||display new data from the db in the ui and wrap it by success- (it) is our data**/
            } catch (throwable: Throwable) {
                query().map {
                    Resource.Error(
                        throwable,
                        it
                    )
                }/**we will still display the cache data if fetch from net failed -(it)is the data*/
            }
        } else {//if it returns false
            query().map { Resource.Success(it) }/**return the cache data without net request*/
        }
    while (true)
    emitAll(flow)//emit whethever comes through the mapped queries
}
