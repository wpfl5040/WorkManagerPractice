# WorkManagerPractice
 - 지연 가능한 비동기 작업(지연 가능한 비동기 작업은 앱이 종료되거나 기기가 다시 시작되더라도 실행될 것으로 예상되는 작업)을 쉽게 예약할 수 있는 API
 - FirebaseJobDispatcher, GcmNetworkManager, JobScheduler를 비롯하여 모든 이전 Android 백그라운드 예약 API를 대체한다는 점에서 적합하고 권장되는 API
 
 ## set up
 ```
 dependencies {
  def work_version = "2.4.0"

    // (Java only)
    implementation "androidx.work:work-runtime:$work_version"

    // Kotlin + coroutines
    implementation "androidx.work:work-runtime-ktx:$work_version"

    // optional - RxJava2 support
    implementation "androidx.work:work-rxjava2:$work_version"

    // optional - GCMNetworkManager support
    implementation "androidx.work:work-gcm:$work_version"

    // optional - Test helpers
    androidTestImplementation "androidx.work:work-testing:$work_version"
  }
  ```
  
  ## 작업 정의 (Worker)
  - 작업은 Worker 클래스를 사용하여 정의합니다.
  - doWork() 메서드는 WorkManager에서 제공하는 백그라운드 스레드에서 동기식으로 실행됩니다.
  ```
    class SimpleWorker : Worker() {
      override fun doWork(): WorkerResult {
        // do something
        return WorkerResult.SUCCESS
      }
    }
   ```
   - Result.success(): 작업이 성공적으로 완료되었습니다.
   - Result.failure(): 작업에 실패했습니다.
   - Result.retry(): 작업에 실패했으며 재시도 정책에 따라 다른 시점에 시도되어야 합니다.
   
   ## WorkRequest
   - 작업을 정의하고 나면 실행을 위해 WorkManager 서비스로 예약
   - 일정한 간격으로 주기적으로 실행되도록 예약하거나 한 번만 실행되도록 예약할 수 있습니다.
   - 구현체로서 OneTimeWorkRequest, PeriodicWorkRequest 가 존재
   ```
   // workRequest 생성
   val uploadWorkRequest: WorkRequest =
   OneTimeWorkRequestBuilder<UploadWorker>()
       .build()
       
   // workRequest 제출
   WorkManager
    .getInstance(myContext)
    .enqueue(uploadWorkRequest)
   ```
   
   
   
   
   # 참고자료
   - https://developer.android.com/topic/libraries/architecture/workmanager/basics?hl=ko
   - https://medium.com/@joongwon/jetpack-android-background%EB%8A%94-workmanager%EC%97%90%EA%B2%8C-%EB%A7%A1%EA%B8%B0%EC%84%B8%EC%9A%94-5f6d97331ff3
   
