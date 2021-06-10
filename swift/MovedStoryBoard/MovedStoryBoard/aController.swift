//
//  aController.swift
//  MovedStoryBoard
//  이동의 대상으로써 간단한 뷰입니다.
//  Created by 윤재필 on 2021/04/20.
//

import UIKit

class aController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func btnDismiss(_ sender: UIButton) {
        dismiss(animated: false, completion: nil)
    }
    
}
