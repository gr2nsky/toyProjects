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
        //getTest()
        getTest2()
        //postTest()
    }

    func getTest() {
        let url = "https://jsonplaceholder.typicode.com/todos/1"
        let alamo = AF.request(url, method: .get, parameters: nil).validate(statusCode: 200..<300)
        AF.request(url,
                   method: .get,
                   parameters: nil,
                   //URL, JSON, PropertyList 세가지 인코디 유형 지원
                   encoding: URLEncoding.default,
                   headers: ["Content-Type":"application/json", "Accept":"application/json"])
            .validate(statusCode: 200..<300)
            .responseJSON { (response) in
                //응답처리
                print("request--------------------------")
                print(response.request)  // original URL request
                print("response--------------------------")
                print(response.response) // URL response
                print("data--------------------------")
                print(response.data)     // server data
                print("result--------------------------")
                print(response.result)   // result of response serialization
        }
    }
    
    func getTest2(){
        let url = "https://jsonplaceholder.typicode.com/todos/1"
        let alamo = AF.request(url, method: .get, parameters: nil).validate(statusCode: 200..<300)

        alamo.responseJSON() { response in
            switch response.result{
            case .success(let value):
                if let jsonObj = value as? [String: Any]{
                    print("데이터의 갯수: \(jsonObj.count)")
                    if let userId = jsonObj["userId"]{
                        print("userId = \(userId)")
                    }
                    if let id = jsonObj["id"]{
                        print("id = \(id)")
                    }
                    if let title = jsonObj["title"]{
                        print("title = \(title)")
                    }
                    if let completed = jsonObj["completed"]{
                        print("completed = \(completed)")
                    }
                    
                }
            case .failure(let error):
                print("error: \(String(describing: error.errorDescription))")
            }
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
                print("error : \(error.errorDescription!)")
            }
        }
    }

}
