package com.smu.sangmyung.quiz.mainquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import com.smu.sangmyung.quiz.BaseActivity
import com.smu.sangmyung.quiz.R
import com.smu.sangmyung.quiz.SmuQuizAIP
import com.smu.sangmyung.quiz.SmuQuizInterface
import com.smu.sangmyung.quiz.model.Quiz
import com.smu.sangmyung.quiz.model.Wrong
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.item_global_title.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.collections.ArrayList


class DailyActivity : BaseActivity() {

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuDailyInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)
    //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
    var choice = mutableListOf<Choice>()

    /*var subject = mutableListOf<QuizSubject>(
        QuizSubject("Database"),
        QuizSubject("Algorighme"),
        QuizSubject("operation_system")
    )*/

    var pr_num = 0 //문제 수
    var quizSolved = false //문제풀이여부
    var wrongBoolean = false //문제정답여부
    var bookMarkBoolean = false //북마크 여부
    var correctAnswer = 0
    var subjectSelect = ArrayList<String>()
    var quiz = arrayListOf<Quiz>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        choice.add(Choice(tvChoice1))
        choice.add(Choice(tvChoice2))
        choice.add(Choice(tvChoice3))
        choice.add(Choice(tvChoice4))
        subjectSelect.clear()
        subjectSelect = intent.getStringArrayListExtra("subject")

        tvPage.visibility = GONE
        callQuiz()
        setBtn()

    }

    //문제 불러오기 && xml설정하기
    @SuppressLint("CheckResult", "SetTextI18n")
    fun callQuiz() {
        smuDailyInterface.getDailyQuiz(subjectSelect.random().toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                sleep(2)
                // 작업 중 오류가 발생하면 이 블록은 호출되지 않습니다
                quiz.add(result[0])
                tvGlobalTitle.text = result[0].subject
                tvMainQuestionContent.text = result[0].title
                tvChoice1.text = "1. ${result[0].choice_1}"
                tvChoice2.text = "2. ${result[0].choice_2}"
                tvChoice3.text = "3. ${result[0].choice_3}"
                tvChoice4.text = "4. ${result[0].choice_4}"
                correctAnswer = result[0].answer

            }, { error ->
                error.printStackTrace()
                Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show()
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete")
            })

        ivAnswerCorrect.visibility = View.GONE
        ivAnswerWrong.visibility = View.GONE

        ivMainLike.setImageResource(R.drawable.like_empty)

        for (m in 0..3) {
            choice[m].tvChoice.setTextColor(Color.BLACK)
            choice[m].tvChoice.setBackgroundColor(Color.TRANSPARENT)
        }
        tvMainQuestionNum.text = "Question ${pr_num + 1}"
    }

    fun isResult(answer: Int, num: Int) {
        if (answer == num) {
            wrongBoolean = true
            ivAnswerCorrect.visibility = View.VISIBLE
            ivAnswerWrong.visibility = View.INVISIBLE
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
            wrongBoolean = false
            ivAnswerCorrect.visibility = View.INVISIBLE
            ivAnswerWrong.visibility = View.VISIBLE
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

    @SuppressLint("CheckResult")
    private fun setBtn() {
        //즐겨찾기 체크 or 해제
        ivMainLike.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (bookMarkBoolean) {
                    bookMarkBoolean = false
                    ivMainLike.setImageResource(R.drawable.like_empty)
                    Log.d("bookmark_true", "$bookMarkBoolean")
                } else {
                    bookMarkBoolean = true
                    ivMainLike.setImageResource(R.drawable.like_fill)
                    Log.d("bookmark_false", "$bookMarkBoolean")
                }
            }
        })

        //문제 풀다가 중간에 그만둘 때
        tvStop.setOnClickListener {
            val intent = Intent(this, StopActivity::class.java)
            startActivityForResult(intent, 3000)
        }

        //다음문제로 넘어가기
        tvNext.setOnClickListener {
            if (quizSolved) {
                if (bookMarkBoolean) {
                    val wrong = Wrong(0, quiz[0].pr_id, getUserEmail())
                    smuDailyInterface.setBookMark(wrong)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ wrong ->
                            Log.d("bookmark", "123123:bookMarkBoolean:$wrong")
                        }, { error ->
                            error.printStackTrace()
                            Log.d("bookmark", "ereerr::bookMarkBoolean")
                        }, { Log.d("bookmark", "complete::bookMarkBoolean") })

                }
                if (!wrongBoolean) {
                    val wrong = Wrong(0, quiz[0].pr_id, getUserEmail())
                    smuDailyInterface.setWrongQuiz(wrong)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ wrong ->
                            Log.d("Result", "123123:wrongBoolean:$wrong")
                        }, { error ->
                            error.printStackTrace()
                            Log.d("Result", "ereerr::wrongBoolean")
                        }, { Log.d("Result", "complete::wrongBoolean") })
                    //오답인경우 전체 오답++
                    saveQuizResult("wr_all",1)
                    Log.d("asd",quiz[0].subject)
                    //해당과목만 오답 증가 ++
                    saveQuizResult("wr_${quiz[0].subject}",1)

                }
                // 전제문제수 증가
                saveQuizResult("all",1)
                // 해당 과목 문제수 증가
                saveQuizResult(quiz[0].subject,1)

                pr_num++
                quiz.clear()
                wrongBoolean = false
                quizSolved = false
                callQuiz()

            } else
                Toast.makeText(this, "문제를 풀어주세요", Toast.LENGTH_LONG).show()
        }
        // 답 선택했을 때 맞는지 틀리는지
        //1번 선택했을 때
        tvChoice1.setOnClickListener {
            isResult(correctAnswer, 1)
            quizSolved = true
        }
        //2번 선택했을 때
        tvChoice2.setOnClickListener {
            isResult(correctAnswer, 2)
            quizSolved = true
        }
        //3번 선택했을 때
        tvChoice3.setOnClickListener {
            isResult(correctAnswer, 3)
            quizSolved = true
        }
        //4번 선택했을 때
        tvChoice4.setOnClickListener {
            isResult(correctAnswer, 4)
            quizSolved = true
        }
    }


    fun <String> ArrayList<String>.radnom(): String {
        var random = Random().nextInt(size)
        return get(random)
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
