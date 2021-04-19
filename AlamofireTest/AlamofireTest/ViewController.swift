//
//  ViewController.swift
//  AlamofireTest
//
//  Created by 윤재필 on 2021/04/19.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        getTest()
        postTest()
    }

    func getTest() {
        let url = "https://jsonplaceholder.typicode.com/todos/1"
        AF.request(url,
                   method: .get,
                   parameters: nil,
                   encoding: URLEncoding.default,
                   headers: ["Content-Type":"application/json", "Accept":"application/json"])
            .validate(statusCode: 200..<300)
            .responseJSON { (json) in
                //여기서 가져온 데이터를 자유롭게 활용하세요.
                print(json)
        }
    }
    
func postTest() {
    //Toilet: g160j-1618823856
    let url = "https://ptsv2.com/t/g160j-1618823856/post"
    var request = URLRequest(url: URL(string: url)!)
    request.httpMethod = "POST"
    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
    request.timeoutInterval = 10
    // POST 로 보낼 정보
    let params = ["id":"아이디", "pw":"패스워드"] as Dictionary

    // httpBody 에 parameters 추가
    do {
        try request.httpBody = JSONSerialization.data(withJSONObject: params, options: [])
        } catch {
            print("http Body Error")
        }
    AF.request(request).responseString { (response) in
        switch response.result {
        case .success:
            print("POST 성공")
        case .failure(let error):
            print("🚫 Alamofire Request Error\nCode:\(error._code), Message: \(error.errorDescription!)")
        }
    }
}

}

