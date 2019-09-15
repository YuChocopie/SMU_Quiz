package com.smu.sangmyung.quiz.mainquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.smu.sangmyung.quiz.BaseActivity
import com.smu.sangmyung.quiz.R
import com.smu.sangmyung.quiz.SmuQuizAIP
import com.smu.sangmyung.quiz.SmuQuizInterface
import com.smu.sangmyung.quiz.model.Quiz
import com.smu.sangmyung.quiz.model.User
import com.smu.sangmyung.quiz.model.Wrong
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.item_global_title.*

class MockTestMain : BaseActivity() {

    var pr_num: Int = 0 // getMocktest[position]에서 position

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuDailyInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    var choice = mutableListOf<Choice>()
    var subjectSelect = arrayListOf<String>()
    var mockList = arrayListOf<Quiz>()
    val subject = HashMap<String, String>()

    var bookMarkBoolean = false
    var wrongBoolean = true
    var quizSolved = false
    var isCorrect = mutableListOf(
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    )


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        callMockList()
        tvStop.text = "prev"
        tvGlobalTitle.text = "Mock Test"

        clickBtn()
        //맞추면 true or 틀리면 false
        //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
        choice.add(Choice(tvChoice1))
        choice.add(Choice(tvChoice2))
        choice.add(Choice(tvChoice3))
        choice.add(Choice(tvChoice4))
        // 선택한 과목
        subjectSelect = intent.getStringArrayListExtra("subject")
        for (i in subjectSelect) {
            subject.put("subject", i)
        }
    }

    @SuppressLint("CheckResult")
    private fun callMockList() {
        smuDailyInterface.getMocktest(subject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Thread.sleep(2)
                for (i in result) {
                    mockList.add(i)
                }
                setQuiz(pr_num)
            }, { error ->
                error.printStackTrace()
                Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show()
                callMockList()
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete")
            })
    }

    @SuppressLint("CheckResult")
    private fun callAddWrong(pr_id: Int) {
        val wrong = Wrong(0, pr_id, getUserEmail())
        smuDailyInterface.setWrongQuiz(wrong)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrong ->
                Log.d("Result", "123123:setBookMark:$wrong")
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr::setBookMark")
            }, { Log.d("Result", "complete::setBookMark") })
    }

    @SuppressLint("CheckResult")
    private fun callAddFavorit(pr_id: Int) {
        val wrong = Wrong(0, pr_id, getUserEmail())
        smuDailyInterface.setBookMark(wrong)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ infoFa ->
                Log.d("Result", "123123:callAddFavorit:$infoFa")
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr::callAddFavorit")
            }, { Log.d("Result", "complete::callAddFavorit") })
    }

    @SuppressLint("CheckResult")
    private fun deleteWrong(pr_id: Int) {
        val userId = User(getUserEmail())
        smuDailyInterface.deleteWrong(pr_id.toString(), userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrong ->
                Log.d("Result", "123123:deleteWrong:$wrong")
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr::deleteWrong")
            }, { Log.d("Result", "complete::deleteWrong") })


    }

    @SuppressLint("CheckResult")
    private fun deleteFavorit(pr_id: Int) {
        val userId = User(getUserEmail())
        smuDailyInterface.deleteFavoirty(pr_id.toString(), userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrong ->
                Log.d("Result", "123123:deleteFavoirty:$wrong")
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr::deleteFavoirty")
            }, { Log.d("Result", "complete::deleteFavoirty") })

    }

    @SuppressLint("CheckResult")
    private fun clickBtn() {
        //즐겨찾기
        ivMainLike.setOnClickListener {
            if (bookMarkBoolean) {
                bookMarkBoolean = false
                ivMainLike.setImageResource(R.drawable.like_fill)
            } else {
                bookMarkBoolean = true
                ivMainLike.setImageResource(R.drawable.like_empty)
            }
        }
        tvStop.setOnClickListener {
            pr_num--
            wrongBoolean = false
            quizSolved = false
            setQuiz(pr_num)
        }
        //다음 문제로 넘어가기
        tvNext.setOnClickListener {
            if (quizSolved) {
                if (bookMarkBoolean) {
                    callAddFavorit(mockList[pr_num].pr_id)
                } else {
                    if (isCorrect[pr_num] != -1) {
//                        deleteFavorit(mockList[pr_num].pr_id)
                    }
                }
                //오답일경우
                if (!wrongBoolean) {
                    //맞으면 1 틀리면 0
                    if (isCorrect[pr_num] == -1) {
                        callAddWrong(mockList[pr_num].pr_id)
                        saveQuizResult("all", 1)
                        saveQuizResult(mockList[pr_num].subject, 1)
                        saveQuizResult("wr_all", 1)
                        saveQuizResult("wr_${mockList[pr_num].subject}", 1)
                    }
                    isCorrect[pr_num] = 0
                } else {
                    //문제 오답인걸 다시 푼 경우 정답일 때 오답개수에서 -1씩 & 오답목록에서 제거
                    if (isCorrect[pr_num] == 0) {
                        deleteWrong(mockList[pr_num].pr_id)
                        saveQuizResult("wr_all", -1)
                        saveQuizResult("wr_${mockList[pr_num].subject}", -1)
                    } else if (isCorrect[pr_num] == -1) {
                        saveQuizResult("all", 1)
                        saveQuizResult(mockList[pr_num].subject, 1)
                    }
                    isCorrect[pr_num] = 1
                }

                pr_num++
                wrongBoolean = false
                quizSolved = false
                if (pr_num < mockList.size) {
                    setQuiz(pr_num)
                }
                //모의고사 다 풀었을 때
                else {
                    var pr_total_correct_num: Int = 0
                    for (i in isCorrect)
                        pr_total_correct_num += i

                    //TotalResult로 총 맞춘문제, 총 문제 수 전달
                    val intent = Intent(this, TotalResult::class.java)
                    intent.putExtra("total_correct_num", pr_total_correct_num) //총 맞춘문제
                    intent.putExtra("total_pr_num", pr_num) // 총 문제 수
                    startActivityForResult(intent, 3000)
                }
            } else
                Toast.makeText(this, "문제를 풀어주세요", Toast.LENGTH_LONG).show()
        }

    }

    //답 맞췄는지 틀렸는지
    private fun isResult(answer: Int, num: Int, choice: MutableList<Choice>) {
        quizSolved = true
        if (answer == num) {
            wrongBoolean = true
            for (m in 0..3) {
                if (m != num - 1) {
                    choice[m].tvChoice.setTextColor(Color.BLACK)
                    choice[m].tvChoice.setBackgroundColor(Color.TRANSPARENT)
                } else {
                    choice[m].tvChoice.setTextColor(Color.BLUE)
                    choice[m].tvChoice.setBackgroundColor(0x6F86FF3B)
                }
            }

        } else {
            wrongBoolean == false
            for (m in 0..3) {
                if (m != num - 1) {
                    choice[m].tvChoice.setTextColor(Color.BLACK)
                    choice[m].tvChoice.setBackgroundColor(Color.TRANSPARENT)
                } else {
                    choice[m].tvChoice.setTextColor(Color.RED)
                    choice[m].tvChoice.setBackgroundColor(0x37FC3846)
                }
            }
        }
    }


    fun setQuiz(pr_num: Int) {

        tvMainQuestionContent.text = mockList[pr_num].title
        tvChoice1.text = "1. ${mockList[pr_num].choice_1}"
        tvChoice2.text = "2. ${mockList[pr_num].choice_2}"
        tvChoice3.text = "3. ${mockList[pr_num].choice_3}"
        tvChoice4.text = "4. ${mockList[pr_num].choice_4}"
        val correctAnswer = mockList[pr_num].answer
        //1번 선택했을 때
        tvChoice1.setOnClickListener {
            isResult(correctAnswer, 1, choice)
        }
        //2번 선택했을 때
        tvChoice2.setOnClickListener {
            isResult(correctAnswer, 2, choice)
        }
        //3번 선택했을 때
        tvChoice3.setOnClickListener {
            isResult(correctAnswer, 3, choice)
        }
        //4번 선택했을 때
        tvChoice4.setOnClickListener {
            isResult(correctAnswer, 4, choice)
        }
        tvPage.text = (pr_num + 1).toString() + "/" + isCorrect.size
        //다음문제로 넘어가면 별 무조건 해제
        ivMainLike.setImageResource(R.drawable.like_empty)

        //다음문제로 넘어가면 choice 글씨 색 모두 블랙으로 바꾸기
        for (m in 0..3) {
            choice[m].tvChoice.setTextColor(Color.BLACK)
            choice[m].tvChoice.setBackgroundColor(Color.TRANSPARENT)
        }
        //문제 증가한거 표시 Question1, Question2, ...
        tvMainQuestionNum.text = "Question ${pr_num + 1}"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                3000 -> finish()
            }
        }
    }

}


