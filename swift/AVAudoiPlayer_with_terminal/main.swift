import Foundation

import AVFoundation

/*
 2021-03-31 10:25:04.335067+0900 testProject[3430:156787] [plugin] AddInstanceForFactory: No factory registered for id <CFUUID 0x1006c48b0> F8BB1C28-BAE8-11D6-9C31-00039315CD46
 실행 실패
 */

var soundEffect: AVAudioPlayer?

loadMusic()

func loadMusic(){
    
    //폴더 디렉토리 지정
    guard let documentsDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first else { return }
    
    //파일명
    let musicFile = "login.mp3"
    
    //폴더 디렉토리에 하위 디렉토리 추가
    var musicURL = documentsDirectory.appendingPathComponent("bgm")
    //해당 디렉토리에 있는 파일 지정
    musicURL = musicURL.appendingPathComponent(musicFile)
    //디렉토리 확인
    print(musicURL)
    print("==============")
    //Old url path - 고전적인 방법의 path지정, url 변환
    //let path = "/Users/greensky/Documents/bgm/login.mp3"
    //let url = URL(fileURLWithPath: path)
    
    //Product폴더 안의 파일 지정
    //let path = "./logoin.mp3"
    //let url = URL(fileURLWithPath: path)
    //print(url)
    //print("==============")
    
    do {
        //해당 url파일을 data형식으로 변환
        //let musicData = try Data(contentsOf: musicURL)
        //AVAidioPlayer에 url 연결해 인스턴스 생성
        soundEffect = try AVAudioPlayer(contentsOf: musicURL)
        //옵셔널 언래핑
        guard let sound = soundEffect else { return }
        //오디오 파일 실팽
        sound.play()
        print("Done")
    } catch let err as NSError {
        print("음악 로딩 에러 : \(err)")
    } catch {
        print ("AVAudioPlayer init failed")
    }
    
}
