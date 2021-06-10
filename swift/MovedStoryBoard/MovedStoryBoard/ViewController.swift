/*
 ViewController.swift
 MovedStoryBoard
 [comment]
 뷰 이동 및 뷰 이동시 modalPresentationStyle 차이 파악
     [pageSheet, formSheet]
         기존 view위에 얹어진 느낌입니다. iphone에서는 차이가 없으나.
         ipad에선 formSheet은 큰 팝업창같이 상하좌우에 여백이 생깁니다.
     [fullScreen]
        화면 전체를 덮습니다.
     [automatic]
        카메라, 이미지보기 등 상황에 맞게 알아서 스타일을 설정합니다.
 Created by 윤재필 on 2021/04/20.
*/

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func btnMove(_ sender: UIButton) {
        
        let sb = UIStoryboard(name: "aController", bundle: nil)
        let aController = sb.instantiateViewController(withIdentifier: "aController") as! aController
        
        aController.modalPresentationStyle = .fullScreen
        self.present(aController,animated: true, completion: nil)
    }
    
    @IBAction func btnPageSheet(_ sender: UIButton) {
        let sb = UIStoryboard(name: "aController", bundle: nil)
        let aController = sb.instantiateViewController(withIdentifier: "aController") as! aController
        
        aController.modalPresentationStyle = .pageSheet
        self.present(aController,animated: true, completion: nil)
    }
    
    @IBAction func btnFormSheet(_ sender: UIButton) {
        let sb = UIStoryboard(name: "aController", bundle: nil)
        let aController = sb.instantiateViewController(withIdentifier: "aController") as! aController
        
        aController.modalPresentationStyle = .formSheet
        self.present(aController,animated: true, completion: nil)
    }
    //지정하지 않을시 .automatic
    @IBAction func btnDefault(_ sender: Any) {
        let sb = UIStoryboard(name: "aController", bundle: nil)
        let aController = sb.instantiateViewController(withIdentifier: "aController") as! aController
    
        self.present(aController,animated: true, completion: nil)
    }
}

