0x00404549:	call 0x00404551
0x00404551:	movl %ebx, $0x55<UINT32>
0x00404556:	call 0x0040455e
0x0040455e:	call 0x004045f2
0x004045f2:	xorl %eax, %eax
0x004045f4:	pushl %fs:(%eax)
0x004045f7:	movl %fs:(%eax), %esp
0x004045fa:	decl %ebx
0x004045fb:	int3
0x00404563:	call 0x0040456b
0x0040456b:	call 0x004045f2
0x00404570:	call 0x00404578
0x00404578:	call 0x00404635
0x00404635:	xorl %eax, %eax
0x00404637:	pushl %fs:(%eax)
0x0040463a:	movl %fs:(%eax), %esp
0x0040463d:	incl %ebx
0x0040463e:	int3
0x0040457d:	call 0x00404585
0x00404585:	call 0x00404635
0x0040458a:	call 0x00404592
0x00404592:	cmpl %ebx, $0x55<UINT8>
0x00404595:	call 0x0040459d
0x0040459d:	jne 0x004045cd
0x004045cd:	call 0x004045e0
0x004045e0:	xorl %eax, %eax
0x004045e2:	pushl %fs:(%eax)
0x004045e5:	movl %fs:(%eax), %esp
0x004045e8:	int3
0x004045d2:	call 0x004045a8
0x004045a8:	pusha
0x004045a9:	call 0x004045ae
0x004045ae:	popl %ebp
0x004045af:	subl %ebp, $0x427394<UINT32>
0x004045b5:	movl %edx, %ebp
0x004045b7:	addl %edx, $0x4273e3<UINT32>
0x004045bd:	pushl %edx
0x004045be:	call 0x004045c4
0x004045c4:	ret

0x004045c3:	ret

0x004045fd:	call 0x00404605
0x00404605:	xorl %ebx, %ebx
0x00404607:	movl %ecx, $0x42a4bf<UINT32>
0x0040460c:	subl %ecx, $0x42748e<UINT32>
0x00404612:	movl %edx, %ebp
0x00404614:	addl %edx, $0x42748e<UINT32>
0x0040461a:	leal %edi, (%edx)
0x0040461c:	movl %esi, %edi
0x0040461e:	xorl %eax, %eax
0x00404620:	call 0x00404628
0x00404628:	call 0x00404644
0x00404644:	lodsb %al, %ds:(%esi)
0x00404645:	rolb %al, $0x78<UINT8>
0x00404648:	stc
0x00404649:	addb %al, $0x61<UINT8>
0x0040464b:	jmp 0x0040464e
0x0040464e:	jmp 0x00404651
0x00404651:	nop
0x00404652:	xorb %al, $0x2c<UINT8>
0x00404654:	jmp 0x00404657
0x00404657:	addb %al, $0xffffff90<UINT8>
0x00404659:	rolb %al, $0x25<UINT8>
0x0040465c:	subb %al, %cl
0x0040465e:	xorb %al, $0x8<UINT8>
0x00404660:	subb %al, %cl
0x00404662:	subb %al, $0x4a<UINT8>
0x00404664:	nop
0x00404665:	addb %al, %cl
0x00404667:	addb %al, %cl
0x00404669:	jmp 0x0040466c
0x0040466c:	jmp 0x0040466f
0x0040466f:	jmp 0x00404672
0x00404672:	jmp 0x00404675
0x00404675:	addb %al, %cl
0x00404677:	jmp 0x0040467a
0x0040467a:	jmp 0x0040467d
0x0040467d:	jmp 0x00404680
0x00404680:	jmp 0x00404683
0x00404683:	jmp 0x00404686
0x00404686:	decb %al
0x00404688:	clc
0x00404689:	jmp 0x0040468c
0x0040468c:	jmp 0x0040468f
0x0040468f:	jmp 0x00404692
0x00404692:	subb %al, $0xffffffb5<UINT8>
0x00404694:	jmp 0x00404697
0x00404697:	rorb %al, $0x4a<UINT8>
0x0040469a:	addb %al, $0x46<UINT8>
0x0040469c:	jmp 0x0040469f
0x0040469f:	clc
0x004046a0:	jmp 0x004046a3
0x004046a3:	clc
0x004046a4:	nop
0x004046a5:	stosb %es:(%edi), %al
0x004046a6:	loop 0x00404644
0x004046a8:	call 0x004046b0
0x004046b0:	xorl %ebx, %ebx
0x004046b2:	movl %ecx, $0x42732f<UINT32>
0x004046b7:	subl %ecx, $0x426e46<UINT32>
0x004046bd:	movl %edx, %ebp
0x004046bf:	addl %edx, $0x426e46<UINT32>
0x004046c5:	leal %edi, (%edx)
0x004046c7:	movl %esi, %edi
0x004046c9:	xorl %eax, %eax
0x004046cb:	call 0x004046d3
0x004046d3:	movl %edx, %ebp
0x004046d5:	addl %edx, $0x4274c9<UINT32>
0x004046db:	leal %eax, (%edx)
0x004046dd:	pushl %eax
0x004046de:	ret

0x004046e3:	lodsb %al, %ds:(%esi)
0x004046e4:	nop
0x004046e5:	jmp 0x004046e8
0x004046e8:	jmp 0x004046eb
0x004046eb:	rorb %al, $0xffffffcb<UINT8>
0x004046ee:	stc
0x004046ef:	jmp 0x004046f2
0x004046f2:	jmp 0x004046f5
0x004046f5:	jmp 0x004046f8
0x004046f8:	decb %al
0x004046fa:	addb %al, $0x31<UINT8>
0x004046fc:	jmp 0x004046ff
0x004046ff:	subb %al, %cl
0x00404701:	clc
0x00404702:	jmp 0x00404705
0x00404705:	nop
0x00404706:	decb %al
0x00404708:	decb %al
0x0040470a:	jmp 0x0040470d
0x0040470d:	rolb %al, $0xffffffd6<UINT8>
0x00404710:	jmp 0x00404713
0x00404713:	jmp 0x00404716
0x00404716:	jmp 0x00404719
0x00404719:	stc
0x0040471a:	jmp 0x0040471d
0x0040471d:	subb %al, $0xffffff92<UINT8>
0x0040471f:	rorb %al, $0x30<UINT8>
0x00404722:	stc
0x00404723:	jmp 0x00404726
0x00404726:	subb %al, $0xffffff8b<UINT8>
0x00404728:	addb %al, %cl
0x0040472a:	jmp 0x0040472d
0x0040472d:	jmp 0x00404730
0x00404730:	nop
0x00404731:	rorb %al, $0x7d<UINT8>
0x00404734:	jmp 0x00404737
0x00404737:	xorb %al, $0x77<UINT8>
0x00404739:	jmp 0x0040473c
0x0040473c:	rolb %al, $0xffffffae<UINT8>
0x0040473f:	nop
0x00404740:	addb %al, %cl
0x00404742:	stc
0x00404743:	nop
0x00404744:	stosb %es:(%edi), %al
0x00404745:	loop 0x004046e3
0x00404747:	movl %eax, 0x42a030(%ebp)
0x0040474d:	call 0x00404755
0x00404755:	pushl %fs:0x30
0x0040475c:	popl %eax
0x0040475d:	testl %eax, %eax
0x0040475f:	js 12
0x00404761:	movl 0x42a3ac(%ebp), $0x1<UINT32>
0x0040476b:	jmp 0x00404777
0x00404777:	leal %eax, 0x42732f(%ebp)
0x0040477d:	movl %ecx, $0x429be3<UINT32>
0x00404782:	subl %ecx, $0x42732f<UINT32>
0x00404788:	pushl %ecx
0x00404789:	pushl %eax
0x0040478a:	call 0x004055ce
0x004055ce:	pushl %ebp
0x004055cf:	movl %ebp, %esp
0x004055d1:	pushl %ebx
0x004055d2:	pushl %ecx
0x004055d3:	pushl %edx
0x004055d4:	pushl %esi
0x004055d5:	pushl %edi
0x004055d6:	movl %eax, %ss:0x8(%ebp)
0x004055da:	movl %ecx, %ss:0xc(%ebp)
0x004055de:	movl %edi, %eax
0x004055e0:	xorl %eax, %eax
0x004055e2:	xorl %ebx, %ebx
0x004055e4:	xorl %edx, %edx
0x004055e6:	movb %al, (%edi)
0x004055e8:	mull %eax, %edx
0x004055ea:	addl %ebx, %eax
0x004055ec:	incl %edx
0x004055ed:	incl %edi
0x004055ee:	loop 0x004055e6
0x004055f0:	xchgl %ebx, %eax
0x004055f1:	popl %edi
0x004055f2:	popl %esi
0x004055f3:	popl %edx
0x004055f4:	popl %ecx
0x004055f5:	popl %ebx
0x004055f6:	movl %esp, %ebp
0x004055f8:	popl %ebp
0x004055f9:	ret

0x0040478f:	addl %esp, $0x8<UINT8>
0x00404792:	movl 0x42a3a8(%ebp), %eax
0x00404798:	leal %esi, 0x42a398(%ebp)
0x0040479e:	leal %eax, 0x4275a7(%ebp)
0x004047a4:	movl %ds:0x8(%esi), %eax
0x004047a8:	movl %edi, %ebp
0x004047aa:	leal %eax, 0x429fb6(%ebp)
0x004047b0:	xorl %ebx, %ebx
0x004047b2:	pushl %eax
0x004047b3:	pushl %fs:(%ebx)
0x004047b6:	movl %fs:(%ebx), %esp
0x004047b9:	movw %ax, $0x4<UINT16>
0x004047bd:	jmp 0x004047c0
0x004047c0:	int3
0x004071d0:	pushl %ebp
0x004071d1:	movl %ebp, %esp
0x004071d3:	pushl %edi
0x004071d4:	movl %eax, %ss:0x10(%ebp)
0x004071d8:	movl %edi, %ds:0x9c(%eax)
0x004071df:	movl %edx, %edi
0x004071e1:	addl %edx, $0x42a3a0<UINT32>
0x004071e7:	pushl %ds:(%edx)
0x004071ea:	popl %ds:0xb8(%eax)
0x004071f1:	movl %ds:0xb4(%eax), %edi
0x004071f8:	movl %ds:0xb0(%eax), $0x4<UINT32>
0x00407203:	movl %eax, $0x0<UINT32>
0x00407208:	popl %edi
0x00407209:	leave
0x0040720a:	ret

0x004047c1:	movl %ebp, %edi
0x004047c3:	xorl %ebx, %ebx
0x004047c5:	popl %fs:(%ebx)
0x004047c8:	addl %esp, $0x4<UINT8>
0x004047cb:	cmpb %al, $0x4<UINT8>
0x004047cd:	je 0x004047d4
0x004047d4:	call 0x004047dc
0x004047dc:	testl 0x42a3a4(%ebp), $0x80<UINT32>
0x004047e6:	je 0x00404819
0x00404819:	movl %eax, 0x42a034(%ebp)
0x0040481f:	addl %eax, 0x3c(%eax)
0x00404822:	addl %eax, $0x80<UINT32>
0x00404827:	movl %ecx, (%eax)
0x00404829:	addl %ecx, 0x42a034(%ebp)
0x0040482f:	addl %ecx, $0x10<UINT8>
0x00404832:	movl %eax, (%ecx)
0x00404834:	addl %eax, 0x42a034(%ebp)
0x0040483a:	movl %ebx, (%eax)
0x0040483c:	movl 0x42722b(%ebp), %ebx
0x00404842:	addl %eax, $0x4<UINT8>
0x00404845:	movl %ebx, (%eax)
0x00404847:	movl 0x42722f(%ebp), %ebx
0x0040484d:	leal %eax, 0x426e46(%ebp)
0x00404853:	pushl %eax
0x00404854:	movl %ebx, $0x42722b<UINT32>
0x00404859:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x0040485d:	call 0x00404865
0x00404865:	movl %esi, %eax
0x00404867:	movl 0x427233(%ebp), %eax
0x0040486d:	call 0x00404875
0x00404875:	leal %eax, 0x426e53(%ebp)
0x0040487b:	call 0x00405310
0x00405310:	pushl %eax
0x00405311:	pushl %esi
0x00405312:	movl %ebx, $0x42722f<UINT32>
0x00405317:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x0040531b:	ret

0x00404880:	movl 0x427237(%ebp), %eax
0x00404886:	call 0x0040488e
0x0040488e:	leal %eax, 0x426e64(%ebp)
0x00404894:	call 0x00405310
0x00404899:	movl 0x42723b(%ebp), %eax
0x0040489f:	call 0x004048a7
0x004048a7:	leal %eax, 0x426e73(%ebp)
0x004048ad:	call 0x00405310
0x004048b2:	movl 0x427243(%ebp), %eax
0x004048b8:	call 0x004048c0
0x004048c0:	leal %eax, 0x426e86(%ebp)
0x004048c6:	call 0x00405310
0x004048cb:	movl 0x427247(%ebp), %eax
0x004048d1:	call 0x004048d9
0x004048d9:	leal %eax, 0x426e92(%ebp)
0x004048df:	call 0x00405310
0x004048e4:	movl 0x42724b(%ebp), %eax
0x004048ea:	call 0x004048f2
0x004048f2:	leal %eax, 0x426e9e(%ebp)
0x004048f8:	call 0x00405310
0x004048fd:	movl 0x42724f(%ebp), %eax
0x00404903:	call 0x0040490b
0x0040490b:	leal %eax, 0x426ea9(%ebp)
0x00404911:	call 0x00405310
0x00404916:	movl 0x427253(%ebp), %eax
0x0040491c:	call 0x00404924
0x00404924:	leal %eax, 0x426eb2(%ebp)
0x0040492a:	call 0x00405310
0x0040492f:	movl 0x427257(%ebp), %eax
0x00404935:	call 0x0040493d
0x0040493d:	leal %eax, 0x426ebe(%ebp)
0x00404943:	call 0x00405310
0x00404948:	movl 0x42725b(%ebp), %eax
0x0040494e:	call 0x00404956
0x00404956:	leal %eax, 0x426eca(%ebp)
0x0040495c:	call 0x00405310
0x00404961:	movl 0x42725f(%ebp), %eax
0x00404967:	call 0x0040496f
0x0040496f:	leal %eax, 0x426edc(%ebp)
0x00404975:	call 0x00405310
0x0040497a:	movl 0x427263(%ebp), %eax
0x00404980:	call 0x00404988
0x00404988:	leal %eax, 0x426ef5(%ebp)
0x0040498e:	call 0x00405310
0x00404993:	movl 0x427267(%ebp), %eax
0x00404999:	call 0x004049a1
0x004049a1:	leal %eax, 0x426f07(%ebp)
0x004049a7:	call 0x00405310
0x004049ac:	movl 0x42726b(%ebp), %eax
0x004049b2:	call 0x004049ba
0x004049ba:	leal %eax, 0x426f1b(%ebp)
0x004049c0:	call 0x00405310
0x004049c5:	movl 0x42726f(%ebp), %eax
0x004049cb:	call 0x004049d3
0x004049d3:	leal %eax, 0x426f2a(%ebp)
0x004049d9:	call 0x00405310
0x004049de:	movl 0x427273(%ebp), %eax
0x004049e4:	call 0x004049ec
0x004049ec:	leal %eax, 0x426f38(%ebp)
0x004049f2:	call 0x00405310
0x004049f7:	movl 0x427277(%ebp), %eax
0x004049fd:	call 0x00404a05
0x00404a05:	leal %eax, 0x426f46(%ebp)
0x00404a0b:	call 0x00405310
0x00404a10:	movl 0x42727b(%ebp), %eax
0x00404a16:	call 0x00404a1e
0x00404a1e:	leal %eax, 0x426f53(%ebp)
0x00404a24:	call 0x00405310
0x00404a29:	movl 0x42727f(%ebp), %eax
0x00404a2f:	call 0x00404a37
0x00404a37:	leal %eax, 0x426f61(%ebp)
0x00404a3d:	call 0x00405310
0x00404a42:	movl 0x427283(%ebp), %eax
0x00404a48:	call 0x00404a50
0x00404a50:	leal %eax, 0x426f6e(%ebp)
0x00404a56:	call 0x00405310
0x00404a5b:	movl 0x427287(%ebp), %eax
0x00404a61:	call 0x00404a69
0x00404a69:	leal %eax, 0x426f79(%ebp)
0x00404a6f:	call 0x00405310
0x00404a74:	movl 0x42728b(%ebp), %eax
0x00404a7a:	call 0x00404a82
0x00404a82:	leal %eax, 0x426f85(%ebp)
0x00404a88:	call 0x00405310
0x00404a8d:	movl 0x42728f(%ebp), %eax
0x00404a93:	call 0x00404a9b
0x00404a9b:	leal %eax, 0x426f96(%ebp)
0x00404aa1:	call 0x00405310
0x00404aa6:	movl 0x427293(%ebp), %eax
0x00404aac:	call 0x00404ab4
0x00404ab4:	leal %eax, 0x426fa7(%ebp)
0x00404aba:	call 0x00405310
0x00404abf:	movl 0x427297(%ebp), %eax
0x00404ac5:	call 0x00404acd
0x00404acd:	leal %eax, 0x426fb8(%ebp)
0x00404ad3:	call 0x00405310
0x00404ad8:	movl 0x42729b(%ebp), %eax
0x00404ade:	call 0x00404ae6
0x00404ae6:	leal %eax, 0x426fcf(%ebp)
0x00404aec:	call 0x00405310
0x00404af1:	movl 0x4272a3(%ebp), %eax
0x00404af7:	call 0x00404aff
0x00404aff:	leal %eax, 0x426fe4(%ebp)
0x00404b05:	call 0x00405310
0x00404b0a:	movl 0x4272a7(%ebp), %eax
0x00404b10:	call 0x00404b18
0x00404b18:	leal %eax, 0x426ff2(%ebp)
0x00404b1e:	call 0x00405310
0x00404b23:	movl 0x4272ab(%ebp), %eax
0x00404b29:	call 0x00404b31
0x00404b31:	leal %eax, 0x427003(%ebp)
0x00404b37:	call 0x00405310
0x00404b3c:	movl 0x4272af(%ebp), %eax
0x00404b42:	call 0x00404b4a
0x00404b4a:	leal %eax, 0x427015(%ebp)
0x00404b50:	call 0x00405310
0x00404b55:	movl 0x4272b3(%ebp), %eax
0x00404b5b:	call 0x00404b63
0x00404b63:	leal %eax, 0x427023(%ebp)
0x00404b69:	call 0x00405310
0x00404b6e:	movl 0x4272b7(%ebp), %eax
0x00404b74:	call 0x00404b7c
0x00404b7c:	leal %eax, 0x427030(%ebp)
0x00404b82:	call 0x00405310
0x00404b87:	movl 0x4272bb(%ebp), %eax
0x00404b8d:	call 0x00404b95
0x00404b95:	leal %eax, 0x42703d(%ebp)
0x00404b9b:	call 0x00405310
0x00404ba0:	movl 0x4272bf(%ebp), %eax
0x00404ba6:	call 0x00404bae
0x00404bae:	leal %eax, 0x42704b(%ebp)
0x00404bb4:	call 0x00405310
0x00404bb9:	movl 0x4272c3(%ebp), %eax
0x00404bbf:	call 0x00404bc7
0x00404bc7:	leal %eax, 0x427058(%ebp)
0x00404bcd:	call 0x00405310
0x00404bd2:	movl 0x4272c7(%ebp), %eax
0x00404bd8:	call 0x00404be0
0x00404be0:	leal %eax, 0x427063(%ebp)
0x00404be6:	call 0x00405310
0x00404beb:	movl 0x4272cb(%ebp), %eax
0x00404bf1:	call 0x00404bf9
0x00404bf9:	leal %eax, 0x427076(%ebp)
0x00404bff:	call 0x00405310
0x00404c04:	movl 0x4272cf(%ebp), %eax
0x00404c0a:	call 0x00404c12
0x00404c12:	leal %eax, 0x42708d(%ebp)
0x00404c18:	pushl %eax
0x00404c19:	movl %ebx, $0x42722b<UINT32>
0x00404c1e:	call LoadLibraryA@kernel32.dll
0x00404c22:	movl %esi, %eax
0x00404c24:	call 0x00404c2c
0x00404c2c:	leal %eax, 0x427098(%ebp)
0x00404c32:	call 0x00405310
0x00404c37:	movl 0x4272d3(%ebp), %eax
0x00404c3d:	call 0x00404c45
0x00404c45:	leal %eax, 0x4270a4(%ebp)
0x00404c4b:	call 0x00405310
0x00404c50:	movl 0x4272d7(%ebp), %eax
0x00404c56:	call 0x00404c5e
0x00404c5e:	leal %eax, 0x4270b1(%ebp)
0x00404c64:	call 0x00405310
0x00404c69:	movl 0x4272db(%ebp), %eax
0x00404c6f:	call 0x00404c77
0x00404c77:	leal %eax, 0x4270c2(%ebp)
0x00404c7d:	call 0x00405310
0x00404c82:	movl 0x4272df(%ebp), %eax
0x00404c88:	call 0x00404c90
0x00404c90:	leal %eax, 0x4270cd(%ebp)
0x00404c96:	call 0x00405310
0x00404c9b:	movl 0x4272e3(%ebp), %eax
0x00404ca1:	call 0x00404ca9
0x00404ca9:	leal %eax, 0x4270dc(%ebp)
0x00404caf:	call 0x00405310
0x00404cb4:	movl 0x4272e7(%ebp), %eax
0x00404cba:	call 0x00404cc2
0x00404cc2:	leal %eax, 0x4270eb(%ebp)
0x00404cc8:	call 0x00405310
0x00404ccd:	movl 0x4272eb(%ebp), %eax
0x00404cd3:	call 0x00404cdb
0x00404cdb:	leal %eax, 0x4270ff(%ebp)
0x00404ce1:	call 0x00405310
0x00404ce6:	movl 0x4272ef(%ebp), %eax
0x00404cec:	call 0x00404cf4
0x00404cf4:	leal %eax, 0x42710b(%ebp)
0x00404cfa:	call 0x00405310
0x00404cff:	movl 0x4272f3(%ebp), %eax
0x00404d05:	call 0x00404d0d
0x00404d0d:	leal %eax, 0x427118(%ebp)
0x00404d13:	pushl %eax
0x00404d14:	movl %ebx, $0x42722b<UINT32>
0x00404d19:	call LoadLibraryA@kernel32.dll
0x00404d1d:	movl %esi, %eax
0x00404d1f:	call 0x00404d27
0x00404d27:	leal %eax, 0x427125(%ebp)
0x00404d2d:	call 0x00405310
0x00404d32:	movl 0x4272f7(%ebp), %eax
0x00404d38:	call 0x00404d40
0x00404d40:	leal %eax, 0x427135(%ebp)
0x00404d46:	call 0x00405310
0x00404d4b:	movl 0x4272fb(%ebp), %eax
0x00404d51:	call 0x00404d59
0x00404d59:	leal %eax, 0x427143(%ebp)
0x00404d5f:	call 0x00405310
0x00404d64:	movl 0x4272ff(%ebp), %eax
0x00404d6a:	call 0x00404d72
0x00404d72:	leal %eax, 0x42714f(%ebp)
0x00404d78:	call 0x00405310
0x00404d7d:	movl 0x427303(%ebp), %eax
0x00404d83:	call 0x00404d8b
0x00404d8b:	leal %eax, 0x42715e(%ebp)
0x00404d91:	call 0x00405310
0x00404d96:	movl 0x427307(%ebp), %eax
0x00404d9c:	call 0x00404da4
0x00404da4:	leal %eax, 0x42716f(%ebp)
0x00404daa:	call 0x00405310
0x00404daf:	movl 0x42730b(%ebp), %eax
0x00404db5:	call 0x00404dbd
0x00404dbd:	leal %eax, 0x427184(%ebp)
0x00404dc3:	call 0x00405310
0x00404dc8:	movl 0x42730f(%ebp), %eax
0x00404dce:	call 0x00404dd6
0x00404dd6:	leal %eax, 0x427198(%ebp)
0x00404ddc:	call 0x00405310
0x00404de1:	movl 0x427313(%ebp), %eax
0x00404de7:	call 0x00404def
0x00404def:	leal %eax, 0x4271a8(%ebp)
0x00404df5:	call 0x00405310
0x00404dfa:	movl 0x427317(%ebp), %eax
0x00404e00:	call 0x00404e08
0x00404e08:	leal %eax, 0x4271b9(%ebp)
0x00404e0e:	call 0x00405310
0x00404e13:	movl 0x42731b(%ebp), %eax
0x00404e19:	call 0x00404e21
0x00404e21:	leal %eax, 0x4271c7(%ebp)
0x00404e27:	call 0x00405310
0x00404e2c:	movl 0x42731f(%ebp), %eax
0x00404e32:	call 0x00404e3a
0x00404e3a:	leal %eax, 0x4271d6(%ebp)
0x00404e40:	call 0x00405310
0x00404e45:	movl 0x427323(%ebp), %eax
0x00404e4b:	call 0x00404e53
0x00404e53:	leal %eax, 0x4271e6(%ebp)
0x00404e59:	call 0x00405310
0x00404e5e:	movl 0x427327(%ebp), %eax
0x00404e64:	call 0x00404e6c
0x00404e6c:	leal %eax, 0x4271f3(%ebp)
0x00404e72:	call 0x00405310
0x00404e77:	movl 0x42732b(%ebp), %eax
0x00404e7d:	call 0x00404e85
0x00404e85:	leal %edi, 0x426e46(%ebp)
0x00404e8b:	movl %ecx, $0x427200<UINT32>
0x00404e90:	subl %ecx, $0x426e46<UINT32>
0x00404e96:	xorb %al, %al
0x00404e98:	rep stosb %es:(%edi), %al
0x00404e9a:	call 0x00404ea2
0x00404ea2:	movl %ebx, $0x4272c3<UINT32>
0x00404ea7:	call GetTickCount@Kernel32.dll
GetTickCount@Kernel32.dll: API Node	
0x00404eab:	movl 0x42a0bc(%ebp), %eax
0x00404eb1:	call 0x00404eb9
0x00404eb9:	movl %ebx, $0x4272c7<UINT32>
0x00404ebe:	call GetVersion@Kernel32.dll
GetVersion@Kernel32.dll: API Node	
0x00404ec2:	testl %eax, $0x80000000<UINT32>
0x00404ec7:	je 0x00404ee9
0x00404ee9:	cmpb %al, $0x3<UINT8>
0x00404eeb:	jne 0x00404ef9
0x00404ef9:	cmpb %al, $0x4<UINT8>
0x00404efb:	jne 0x00404f09
0x00404f09:	cmpb %al, $0x5<UINT8>
0x00404f0b:	jne 0x00404f19
0x00404f19:	movl %ebx, $0x4272eb<UINT32>
0x00404f1e:	call GetForegroundWindow@User32.dll
GetForegroundWindow@User32.dll: API Node	
0x00404f22:	movl 0x42a04c(%ebp), %eax
0x00404f28:	pushl $0x0<UINT8>
0x00404f2a:	leal %eax, 0x42a4a9(%ebp)
0x00404f30:	pushl %eax
0x00404f31:	movl %ebx, $0x4272ef<UINT32>
0x00404f36:	call FindWindowA@User32.dll
FindWindowA@User32.dll: API Node	
0x00404f3a:	movl 0x42a050(%ebp), %eax
0x00404f40:	movl %ebx, $0x4272f3<UINT32>
0x00404f45:	call GetTopWindow@User32.dll
GetTopWindow@User32.dll: API Node	
0x00404f49:	movl 0x42a058(%ebp), %eax
0x00404f4f:	call 0x00404f57
0x00404f57:	movl %ebx, $0x427267<UINT32>
0x00404f5c:	call GetCurrentProcess@Kernel32.dll
GetCurrentProcess@Kernel32.dll: API Node	
0x00404f60:	pushl %eax
0x00404f61:	pushl %eax
0x00404f62:	movl %ebx, $0x427297<UINT32>
0x00404f67:	call GetPriorityClass@Kernel32.dll
GetPriorityClass@Kernel32.dll: API Node	
0x00404f6b:	movl 0x42a060(%ebp), %eax
0x00404f71:	popl %eax
0x00404f72:	pushl $0x80<UINT32>
0x00404f77:	pushl %eax
0x00404f78:	movl %ebx, $0x427293<UINT32>
0x00404f7d:	call SetPriorityClass@Kernel32.dll
SetPriorityClass@Kernel32.dll: API Node	
0x00404f81:	testl 0x42a048(%ebp), $0x8<UINT32>
0x00404f8b:	jne 11
0x00404f8d:	pushl $0x1<UINT8>
0x00404f8f:	movl %ebx, $0x4272df<UINT32>
0x00404f94:	call BlockInput@User32.dll
BlockInput@User32.dll: API Node	
0x00404f98:	movl %edx, $0x0<UINT32>
0x00404f9d:	testl 0x42a068(%ebp), $0x1<UINT32>
0x00404fa7:	jne 5
0x00404fa9:	movl %edx, $0xf0000000<UINT32>
0x00404fae:	pushl %edx
0x00404faf:	pushl $0x1<UINT8>
0x00404fb1:	leal %eax, 0x427200(%ebp)
0x00404fb7:	pushl %eax
0x00404fb8:	pushl $0x0<UINT8>
0x00404fba:	leal %eax, 0x42a064(%ebp)
0x00404fc0:	pushl %eax
0x00404fc1:	movl %ebx, $0x42730b<UINT32>
0x00404fc6:	call CryptAcquireContextA@Advapi32.dll
CryptAcquireContextA@Advapi32.dll: API Node	
0x00404fca:	testl %eax, %eax
0x00404fcc:	jne 0x0040501a
0x0040501a:	leal %eax, 0x42a074(%ebp)
0x00405020:	pushl %eax
0x00405021:	pushl $0x0<UINT8>
0x00405023:	pushl $0x0<UINT8>
0x00405025:	movl %eax, 0x42a06c(%ebp)
0x0040502b:	pushl %eax
0x0040502c:	movl %eax, 0x42a064(%ebp)
0x00405032:	pushl %eax
0x00405033:	movl %ebx, $0x427313<UINT32>
0x00405038:	call CryptCreateHash@Advapi32.dll
CryptCreateHash@Advapi32.dll: API Node	
0x0040503c:	testl %eax, %eax
0x0040503e:	je 217
0x00405044:	leal %esi, 0x42a07c(%ebp)
0x0040504a:	pushl %esi
0x0040504b:	call 0x004061d3
0x004061d3:	pushl %ebp
0x004061d4:	movl %ebp, %esp
0x004061d6:	pushl %ecx
0x004061d7:	pushl %esi
0x004061d8:	pushl %ebx
0x004061d9:	movl %esi, %ss:0x8(%ebp)
0x004061dd:	movl %ecx, $0xff<UINT32>
0x004061e2:	xorl %ebx, %ebx
0x004061e4:	lodsb %al, %ds:(%esi)
0x004061e5:	cmpb %al, $0x0<UINT8>
0x004061e7:	je 0x004061ec
0x004061e9:	incl %ebx
0x004061ea:	loop 0x004061e4
0x004061ec:	movl %eax, %ebx
0x004061ee:	popl %ebx
0x004061ef:	popl %esi
0x004061f0:	popl %ecx
0x004061f1:	movl %esp, %ebp
0x004061f3:	popl %ebp
0x004061f4:	ret

0x00405050:	addl %esp, $0x4<UINT8>
0x00405053:	pushl $0x0<UINT8>
0x00405055:	pushl %eax
0x00405056:	leal %eax, 0x42a07c(%ebp)
0x0040505c:	pushl %eax
0x0040505d:	movl %eax, 0x42a074(%ebp)
0x00405063:	pushl %eax
0x00405064:	movl %ebx, $0x42731b<UINT32>
0x00405069:	call CryptHashData@Advapi32.dll
CryptHashData@Advapi32.dll: API Node	
0x0040506d:	testl %eax, %eax
0x0040506f:	je 168
0x00405075:	leal %eax, 0x42a078(%ebp)
0x0040507b:	pushl %eax
0x0040507c:	pushl $0x0<UINT8>
0x0040507e:	movl %eax, 0x42a074(%ebp)
0x00405084:	pushl %eax
0x00405085:	movl %eax, 0x42a070(%ebp)
0x0040508b:	pushl %eax
0x0040508c:	movl %eax, 0x42a064(%ebp)
0x00405092:	pushl %eax
0x00405093:	movl %ebx, $0x42731f<UINT32>
0x00405098:	call CryptDeriveKey@Advapi32.dll
CryptDeriveKey@Advapi32.dll: API Node	
0x0040509c:	testl %eax, %eax
0x0040509e:	jne 0x0040511d
0x0040511d:	movl %eax, 0x42a074(%ebp)
0x00405123:	pushl %eax
0x00405124:	movl %ebx, $0x427317<UINT32>
0x00405129:	call CryptDestroyHash@Advapi32.dll
CryptDestroyHash@Advapi32.dll: API Node	
0x0040512d:	call 0x00405135
0x00405135:	testl 0x42a3a4(%ebp), $0x1<UINT32>
0x0040513f:	je 124
0x00405141:	leal %edi, 0x42a496(%ebp)
0x00405147:	pushl $0x0<UINT8>
0x00405149:	pushl $0x80<UINT32>
0x0040514e:	pushl $0x3<UINT8>
0x00405150:	pushl $0x0<UINT8>
0x00405152:	pushl $0x3<UINT8>
0x00405154:	pushl $0xc0000000<UINT32>
0x00405159:	pushl %edi
0x0040515a:	movl %ebx, $0x427247<UINT32>
0x0040515f:	call CreateFileA@Kernel32.dll
CreateFileA@Kernel32.dll: API Node	
0x00405163:	cmpl %eax, $0xffffffff<UINT8>
0x00405166:	je 0x0040517f
0x0040517f:	leal %edi, 0x42a49f(%ebp)
0x00405185:	pushl $0x0<UINT8>
0x00405187:	pushl $0x80<UINT32>
0x0040518c:	pushl $0x3<UINT8>
0x0040518e:	pushl $0x0<UINT8>
0x00405190:	pushl $0x3<UINT8>
0x00405192:	pushl $0xc0000000<UINT32>
0x00405197:	pushl %edi
0x00405198:	movl %ebx, $0x427247<UINT32>
0x0040519d:	call CreateFileA@Kernel32.dll
0x004051a1:	cmpl %eax, $0xffffffff<UINT8>
0x004051a4:	je 0x004051bd
0x004051bd:	call 0x004051c5
0x004051c5:	testl 0x42a048(%ebp), $0x8<UINT32>
0x004051cf:	je 0x004051d9
0x004051d9:	movl %ecx, $0x49<UINT32>
0x004051de:	movl %edx, %ebp
0x004051e0:	addl %edx, $0x42a4ef<UINT32>
0x004051e6:	xorl %eax, %eax
0x004051e8:	leal %edi, (%edx)
0x004051ea:	pushl %edi
0x004051eb:	rep stosl %es:(%edi), %eax
0x004051ed:	popl %edi
0x004051ee:	movl %ss:(%edx), $0x128<UINT32>
0x004051f5:	movl %edx, %ebp
0x004051f7:	addl %edx, $0x42726b<UINT32>
0x004051fd:	call GetCurrentProcessId@Kernel32.dll
GetCurrentProcessId@Kernel32.dll: API Node	
0x004051ff:	pushl $0x0<UINT8>
0x00405201:	pushl $0x2<UINT8>
0x00405203:	movl %edx, %ebp
0x00405205:	addl %edx, $0x42a4e3<UINT32>
0x0040520b:	movl (%edx), %eax
0x0040520d:	movl %edx, %ebp
0x0040520f:	addl %edx, $0x427263<UINT32>
0x00405215:	call CreateToolhelp32Snapshot@Kernel32.dll
CreateToolhelp32Snapshot@Kernel32.dll: API Node	
0x00405217:	movl %esi, %eax
0x00405219:	movl %eax, %ebp
0x0040521b:	addl %eax, $0x42a4ef<UINT32>
0x00405220:	pushl %eax
0x00405221:	pushl %esi
0x00405222:	movl %edx, %ebp
0x00405224:	addl %edx, $0x42726f<UINT32>
0x0040522a:	call Process32First@Kernel32.dll
Process32First@Kernel32.dll: API Node	
0x0040522c:	testl %eax, %eax
0x0040522e:	je 179
0x00405234:	movl %edx, %ebp
0x00405236:	addl %edx, $0x42a4ef<UINT32>
0x0040523c:	leal %ecx, (%edx)
0x0040523e:	pushl %ecx
0x0040523f:	pushl %esi
0x00405240:	movl %edx, %ebp
0x00405242:	addl %edx, $0x427273<UINT32>
0x00405248:	call Process32Next@Kernel32.dll
Process32Next@Kernel32.dll: API Node	
0x0040524a:	testl %eax, %eax
0x0040524c:	je 149
0x00405252:	xorl %ebx, %ebx
0x00405254:	jmp 0x00405259
0x00405259:	pushl %esi
0x0040525a:	movl %edx, %ebp
0x0040525c:	addl %edx, $0x42a4ef<UINT32>
0x00405262:	movl %eax, 0x8(%edx)
0x00405265:	leal %esi, 0x24(%edx)
0x00405268:	movl %edi, %esi
0x0040526a:	pushl %esi
0x0040526b:	pushl %edi
0x0040526c:	call 0x004061a3
0x004061a3:	pushl %ebp
0x004061a4:	movl %ebp, %esp
0x004061a6:	pushl %ecx
0x004061a7:	pushl %eax
0x004061a8:	pushl %esi
0x004061a9:	pushl %edi
0x004061aa:	movl %edi, %ss:0x8(%ebp)
0x004061ae:	movl %esi, %ss:0xc(%ebp)
0x004061b2:	movl %ecx, $0xff<UINT32>
0x004061b7:	xorl %eax, %eax
0x004061b9:	lodsb %al, %ds:(%esi)
0x004061ba:	cmpb %al, $0x0<UINT8>
0x004061bc:	je 0x004061cb
0x004061be:	cmpb %al, $0x60<UINT8>
0x004061c0:	jb 0x004061c8
0x004061c2:	cmpb %al, $0x7b<UINT8>
0x004061c4:	jae 2
0x004061c6:	subb %al, $0x20<UINT8>
0x004061c8:	stosb %es:(%edi), %al
0x004061c9:	loop 0x004061b9
0x004061cb:	popl %edi
0x004061cc:	popl %esi
0x004061cd:	popl %eax
0x004061ce:	popl %ecx
0x004061cf:	movl %esp, %ebp
0x004061d1:	popl %ebp
0x004061d2:	ret

0x00405271:	addl %esp, $0x8<UINT8>
0x00405274:	pushl %edi
0x00405275:	pushl %esi
0x00405276:	call 0x0040635f
0x0040635f:	pushl %ebp
0x00406360:	movl %ebp, %esp
0x00406362:	pushl %edx
0x00406363:	pushl %ecx
0x00406364:	pushl %ebx
0x00406365:	pushl %eax
0x00406366:	pushl %esi
0x00406367:	movl %esi, %ss:0x8(%ebp)
0x0040636b:	movl %edi, %esi
0x0040636d:	movl %ecx, $0xff<UINT32>
0x00406372:	xorl %eax, %eax
0x00406374:	xorl %ebx, %ebx
0x00406376:	xorl %edx, %edx
0x00406378:	lodsb %al, %ds:(%esi)
0x00406379:	cmpb %al, $0x0<UINT8>
0x0040637b:	je 0x00406386
0x0040637d:	cmpb %al, $0x5c<UINT8>
0x0040637f:	jne 0x00406383
0x00406383:	incl %ebx
0x00406384:	loop 0x00406378
0x00406386:	cmpl %edx, $0x0<UINT8>
0x00406389:	je 0x0040638c
0x0040638c:	addl %edi, %edx
0x0040638e:	movl %ss:0xc(%ebp), %edi
0x00406392:	popl %esi
0x00406393:	popl %eax
0x00406394:	popl %ebx
0x00406395:	popl %ecx
0x00406396:	popl %edx
0x00406397:	movl %esp, %ebp
0x00406399:	popl %ebp
0x0040639a:	ret

0x0040527b:	addl %esp, $0x8<UINT8>
0x0040527e:	movl %esi, %edi
0x00405280:	pushl %esi
0x00405281:	pushl %edi
0x00405282:	movl %edx, %ebp
0x00405284:	addl %edx, $0x42a489<UINT32>
0x0040528a:	leal %edi, (%edx)
0x0040528c:	movl %ecx, $0xd<UINT32>
0x00405291:	xorl %edx, %edx
0x00405293:	rep cmpsb %es:(%edi), %ds:(%esi)
0x00405295:	jne 0x004052a0
0x004052a0:	popl %edi
0x004052a1:	popl %esi
0x004052a2:	pushl %edx
0x004052a3:	movl %edx, %ebp
0x004052a5:	addl %edx, $0x42a4e3<UINT32>
0x004052ab:	cmpl %eax, %ss:(%edx)
0x004052ae:	popl %edx
0x004052af:	jne 0x004052c8
0x004052c8:	popl %esi
0x004052c9:	movl %edx, %ebp
0x004052cb:	addl %edx, $0x42a4ef<UINT32>
0x004052d1:	leal %ecx, (%edx)
0x004052d3:	pushl %ecx
0x004052d4:	pushl %esi
0x004052d5:	movl %edx, %ebp
0x004052d7:	addl %edx, $0x427273<UINT32>
0x004052dd:	call Process32Next@Kernel32.dll
0x004052df:	testl %eax, %eax
0x004052e1:	jne 0x00405259
0x004052b1:	pushl %edx
0x004052b2:	movl %edx, %ebp
0x004052b4:	addl %edx, $0x42a4ef<UINT32>
0x004052ba:	movl %eax, 0x18(%edx)
0x004052bd:	movl %edx, %ebp
0x004052bf:	addl %edx, $0x42a03c<UINT32>
0x004052c5:	movl (%edx), %eax
0x004052c7:	popl %edx
0x004052e7:	movl %edx, %ebp
0x004052e9:	addl %edx, $0x42a040<UINT32>
0x004052ef:	movl (%edx), %ebx
0x004052f1:	pushl %esi
0x004052f2:	movl %edx, %ebp
0x004052f4:	addl %edx, $0x42725b<UINT32>
0x004052fa:	call CloseHandle@Kernel32.dll
CloseHandle@Kernel32.dll: API Node	
0x004052fc:	movl %edx, %ebp
0x004052fe:	addl %edx, $0x428102<UINT32>
0x00405304:	leal %eax, (%edx)
0x00405306:	pushl %eax
0x00405307:	ret

0x0040531c:	testl 0x42a3a4(%ebp), $0x80<UINT32>
0x00405326:	jne 69
0x00405328:	movl %eax, 0x42a040(%ebp)
0x0040532e:	movl %ebx, 0x42a03c(%ebp)
0x00405334:	cmpl %eax, %ebx
0x00405336:	je 53
0x00405338:	testl 0x42a048(%ebp), $0x8<UINT32>
0x00405342:	je 0x0040534c
0x0040534c:	call 0x00406a58
0x00406a58:	testl 0x42a048(%ebp), $0x8<UINT32>
0x00406a62:	je 0x00406a65
0x00406a65:	xorl %eax, %eax
0x00406a67:	movl %ecx, $0x49<UINT32>
0x00406a6c:	movl %edx, %ebp
0x00406a6e:	addl %edx, $0x42a4ef<UINT32>
0x00406a74:	leal %edi, (%edx)
0x00406a76:	pushl %edi
0x00406a77:	rep stosl %es:(%edi), %eax
0x00406a79:	popl %edi
0x00406a7a:	movl %ss:(%edx), $0x128<UINT32>
0x00406a81:	movl %edx, %ebp
0x00406a83:	addl %edx, $0x42726b<UINT32>
0x00406a89:	call GetCurrentProcessId@Kernel32.dll
0x00406a8b:	pushl $0x0<UINT8>
0x00406a8d:	pushl $0x2<UINT8>
0x00406a8f:	movl %edx, %ebp
0x00406a91:	addl %edx, $0x42a4e3<UINT32>
0x00406a97:	movl (%edx), %eax
0x00406a99:	movl %edx, %ebp
0x00406a9b:	addl %edx, $0x427263<UINT32>
0x00406aa1:	call CreateToolhelp32Snapshot@Kernel32.dll
0x00406aa3:	movl %esi, %eax
0x00406aa5:	movl %eax, %ebp
0x00406aa7:	addl %eax, $0x42a4ef<UINT32>
0x00406aac:	pushl %eax
0x00406aad:	pushl %esi
0x00406aae:	movl %edx, %ebp
0x00406ab0:	addl %edx, $0x42726f<UINT32>
0x00406ab6:	call Process32First@Kernel32.dll
0x00406ab8:	testl %eax, %eax
0x00406aba:	je 110
0x00406abc:	movl %edx, %ebp
0x00406abe:	addl %edx, $0x42a4ef<UINT32>
0x00406ac4:	leal %ecx, (%edx)
0x00406ac6:	pushl %ecx
0x00406ac7:	pushl %esi
0x00406ac8:	movl %edx, %ebp
0x00406aca:	addl %edx, $0x427273<UINT32>
0x00406ad0:	call Process32Next@Kernel32.dll
0x00406ad2:	testl %eax, %eax
0x00406ad4:	je 84
0x00406ad6:	xorl %ebx, %ebx
0x00406ad8:	jmp 0x00406add
0x00406add:	pushl %esi
0x00406ade:	movl %edx, %ebp
0x00406ae0:	addl %edx, $0x42a4ef<UINT32>
0x00406ae6:	movl %eax, 0x8(%edx)
0x00406ae9:	pushl %eax
0x00406aea:	pushl $0x1<UINT8>
0x00406aec:	pushl $0x1f0fff<UINT32>
0x00406af1:	movl %edx, %ebp
0x00406af3:	addl %edx, $0x42728b<UINT32>
0x00406af9:	call OpenProcess@Kernel32.dll
OpenProcess@Kernel32.dll: API Node	
0x00406afb:	pushl $0x100<UINT32>
0x00406b00:	pushl %eax
0x00406b01:	movl %edx, %ebp
0x00406b03:	addl %edx, $0x427293<UINT32>
0x00406b09:	call SetPriorityClass@Kernel32.dll
0x00406b0b:	popl %esi
0x00406b0c:	movl %edx, %ebp
0x00406b0e:	addl %edx, $0x42a4ef<UINT32>
0x00406b14:	leal %ecx, (%edx)
0x00406b16:	pushl %ecx
0x00406b17:	pushl %esi
0x00406b18:	movl %edx, %ebp
0x00406b1a:	addl %edx, $0x427273<UINT32>
0x00406b20:	call Process32Next@Kernel32.dll
0x00406b22:	testl %eax, %eax
0x00406b24:	jne 0x004069c2
0x004069c2:	pushl %esi
0x004069c3:	movl %edx, %ebp
0x004069c5:	addl %edx, $0x42a4ef<UINT32>
0x004069cb:	movl %eax, 0x8(%edx)
0x004069ce:	leal %esi, 0x24(%edx)
0x004069d1:	movl %edi, %esi
0x004069d3:	pushl %esi
0x004069d4:	pushl %edi
0x004069d5:	call 0x004061a3
0x004069da:	addl %esp, $0x8<UINT8>
0x004069dd:	pushl %edi
0x004069de:	pushl %esi
0x004069df:	call 0x0040635f
0x004069e4:	addl %esp, $0x8<UINT8>
0x004069e7:	movl %esi, %edi
0x004069e9:	movl %edx, %ebp
0x004069eb:	addl %edx, $0x42a489<UINT32>
0x004069f1:	leal %edi, (%edx)
0x004069f3:	movl %ecx, $0xd<UINT32>
0x004069f8:	xorl %edx, %edx
0x004069fa:	rep cmpsb %es:(%edi), %ds:(%esi)
0x004069fc:	jne 0x00406a07
0x00406a07:	pushl %edx
0x00406a08:	movl %edx, %ebp
0x00406a0a:	addl %edx, $0x42a4e3<UINT32>
0x00406a10:	cmpl %eax, %ss:(%edx)
0x00406a13:	popl %edx
0x00406a14:	jne 0x00406a2d
0x00406a2d:	popl %esi
0x00406a2e:	movl %edx, %ebp
0x00406a30:	addl %edx, $0x42a4ef<UINT32>
0x00406a36:	leal %ecx, (%edx)
0x00406a38:	pushl %ecx
0x00406a39:	pushl %esi
0x00406a3a:	movl %edx, %ebp
0x00406a3c:	addl %edx, $0x427273<UINT32>
0x00406a42:	call Process32Next@Kernel32.dll
0x00406a44:	testl %eax, %eax
0x00406a46:	jne 0x004069c2
0x00406a16:	pushl %edx
0x00406a17:	movl %edx, %ebp
0x00406a19:	addl %edx, $0x42a4ef<UINT32>
0x00406a1f:	movl %eax, 0x18(%edx)
0x00406a22:	movl %edx, %ebp
0x00406a24:	addl %edx, $0x42a03c<UINT32>
0x00406a2a:	movl (%edx), %eax
0x00406a2c:	popl %edx
0x00406a4c:	pushl %esi
0x00406a4d:	movl %edx, %ebp
0x00406a4f:	addl %edx, $0x42725b<UINT32>
0x00406a55:	call CloseHandle@Kernel32.dll
0x00406a57:	ret

0x00405351:	testl 0x42a048(%ebp), $0x2<UINT32>
0x0040535b:	jne 16
0x0040535d:	movl %ebx, $0x1<UINT32>
0x00405362:	movl %eax, 0x42a03c(%ebp)
0x00405368:	call 0x00405708
0x00405708:	movl %edx, %ebp
0x0040570a:	addl %edx, $0x4285fe<UINT32>
0x00405710:	movl (%edx), %eax
0x00405712:	movl %ecx, $0x49<UINT32>
0x00405717:	movl %edx, %ebp
0x00405719:	addl %edx, $0x42a4ef<UINT32>
0x0040571f:	xorl %eax, %eax
0x00405721:	leal %edi, (%edx)
0x00405723:	pushl %edi
0x00405724:	rep stosl %es:(%edi), %eax
0x00405726:	popl %edi
0x00405727:	movl %ss:(%edx), $0x1c<UINT32>
0x0040572e:	movl %edx, %ebp
0x00405730:	addl %edx, $0x4285fe<UINT32>
0x00405736:	movl %eax, (%edx)
0x00405738:	pushl %eax
0x00405739:	pushl $0x4<UINT8>
0x0040573b:	movl %edx, %ebp
0x0040573d:	addl %edx, $0x427263<UINT32>
0x00405743:	call CreateToolhelp32Snapshot@Kernel32.dll
0x00405745:	movl %esi, %eax
0x00405747:	movl %eax, %ebp
0x00405749:	addl %eax, $0x42a4ef<UINT32>
0x0040574e:	pushl %eax
0x0040574f:	pushl %esi
0x00405750:	movl %edx, %ebp
0x00405752:	addl %edx, $0x42727f<UINT32>
0x00405758:	call Thread32First@Kernel32.dll
Thread32First@Kernel32.dll: API Node	
0x0040575a:	testl %eax, %eax
0x0040575c:	je 166
0x00405762:	movl %edx, %ebp
0x00405764:	addl %edx, $0x42a4ef<UINT32>
0x0040576a:	leal %ecx, (%edx)
0x0040576c:	pushl %ecx
0x0040576d:	pushl %esi
0x0040576e:	movl %edx, %ebp
0x00405770:	addl %edx, $0x427283<UINT32>
0x00405776:	call Thread32Next@Kernel32.dll
Thread32Next@Kernel32.dll: API Node	
0x00405778:	testl %eax, %eax
0x0040577a:	je 136
0x00405780:	pushl %esi
0x00405781:	movl %edx, %ebp
0x00405783:	addl %edx, $0x42a4ef<UINT32>
0x00405789:	movl %ecx, 0xc(%edx)
0x0040578c:	movl %edx, %ebp
0x0040578e:	addl %edx, $0x4285fe<UINT32>
0x00405794:	movl %eax, (%edx)
0x00405796:	cmpl %ecx, %eax
0x00405798:	jne 0x004057e9
0x004057e9:	popl %esi
0x004057ea:	movl %edx, %ebp
0x004057ec:	addl %edx, $0x42a4ef<UINT32>
0x004057f2:	leal %ecx, (%edx)
0x004057f4:	pushl %ecx
0x004057f5:	pushl %esi
0x004057f6:	movl %edx, %ebp
0x004057f8:	addl %edx, $0x427283<UINT32>
0x004057fe:	call Thread32Next@Kernel32.dll
0x00405800:	testl %eax, %eax
0x00405802:	jne 0x00405780
0x0040579a:	movl %edx, %ebp
0x0040579c:	addl %edx, $0x42a4ef<UINT32>
0x004057a2:	movl %eax, 0x8(%edx)
0x004057a5:	pushl %eax
0x004057a6:	pushl $0x0<UINT8>
0x004057a8:	pushl $0x2<UINT8>
0x004057aa:	movl %edx, %ebp
0x004057ac:	addl %edx, $0x427287<UINT32>
0x004057b2:	call OpenThread@Kernel32.dll
OpenThread@Kernel32.dll: API Node	
0x004057b4:	testl %eax, %eax
0x004057b6:	je 49
0x004057b8:	movl %ecx, %eax
0x004057ba:	testl %ebx, %ebx
0x004057bc:	je 17
0x004057be:	pushl %ecx
0x004057bf:	movl %eax, %ecx
0x004057c1:	pushl %eax
0x004057c2:	movl %edx, %ebp
0x004057c4:	addl %edx, $0x4272b3<UINT32>
0x004057ca:	call SuspendThread@Kernel32.dll
SuspendThread@Kernel32.dll: API Node	
0x004057cc:	popl %ecx
0x004057cd:	jmp 0x004057de
0x004057de:	pushl %ecx
0x004057df:	movl %edx, %ebp
0x004057e1:	addl %edx, $0x42725b<UINT32>
0x004057e7:	call CloseHandle@Kernel32.dll
0x00405808:	movl %eax, $0x55555555<UINT32>
0x0040580d:	movl %edx, %ebp
0x0040580f:	addl %edx, $0x4285fe<UINT32>
0x00405815:	movl (%edx), %eax
0x00405817:	ret

0x0040536d:	pushl $0xfffffff0<UINT8>
0x0040536f:	movl %eax, 0x42a050(%ebp)
0x00405375:	pushl %eax
0x00405376:	movl %ebx, $0x4272e3<UINT32>
0x0040537b:	call GetWindowLongA@User32.dll
GetWindowLongA@User32.dll: API Node	
0x0040537f:	movl 0x42a054(%ebp), %eax
0x00405385:	orl %eax, $0x8000000<UINT32>
0x0040538a:	pushl %eax
0x0040538b:	pushl $0xfffffff0<UINT8>
0x0040538d:	movl %eax, 0x42a050(%ebp)
0x00405393:	pushl %eax
0x00405394:	movl %ebx, $0x4272e7<UINT32>
0x00405399:	call SetWindowLongA@User32.dll
SetWindowLongA@User32.dll: API Node	
0x0040539d:	pushl $0xfffffff0<UINT8>
0x0040539f:	movl %eax, 0x42a058(%ebp)
0x004053a5:	pushl %eax
0x004053a6:	movl %ebx, $0x4272e3<UINT32>
0x004053ab:	call GetWindowLongA@User32.dll
0x004053af:	movl 0x42a05c(%ebp), %eax
0x004053b5:	orl %eax, $0x8000000<UINT32>
0x004053ba:	pushl %eax
0x004053bb:	pushl $0xfffffff0<UINT8>
0x004053bd:	movl %eax, 0x42a058(%ebp)
0x004053c3:	pushl %eax
0x004053c4:	movl %ebx, $0x4272e7<UINT32>
0x004053c9:	call SetWindowLongA@User32.dll
0x004053cd:	testl 0x42a3a4(%ebp), $0x10<UINT32>
0x004053d7:	je 58
0x004053d9:	pushl %fs:0x30
0x004053e0:	popl %eax
0x004053e1:	testl %eax, %eax
0x004053e3:	js 15
0x004053e5:	movl %eax, 0xc(%eax)
0x004053e8:	movl %eax, 0xc(%eax)
0x004053eb:	movl 0x20(%eax), $0x2000<UINT32>
0x004053f2:	jmp 0x00405413
0x00405413:	testl 0x42a048(%ebp), $0x8<UINT32>
0x0040541d:	je 0x00405427
0x00405427:	movl %eax, 0x42a03c(%ebp)
0x0040542d:	orl %eax, %eax
0x0040542f:	jne 0x00405433
0x00405433:	call 0x0040543b
0x0040543b:	movl %edi, 0x42a034(%ebp)
0x00405441:	addl %edi, 0x3c(%edi)
0x00405444:	movl %esi, 0x42a034(%ebp)
0x0040544a:	movl %ecx, 0x54(%edi)
0x0040544d:	leal %eax, 0x42a4ef(%ebp)
0x00405453:	pushl %eax
0x00405454:	pushl $0x4<UINT8>
0x00405456:	pushl %ecx
0x00405457:	pushl 0x42a034(%ebp)
0x0040545d:	movl %ebx, $0x42723b<UINT32>
0x00405462:	call VirtualProtect@Kernel32.dll
VirtualProtect@Kernel32.dll: API Node	
0x00405466:	testl 0x42a3a4(%ebp), $0x8<UINT32>
0x00405470:	je 419
0x00405476:	testl 0x42a3a4(%ebp), $0x80<UINT32>
0x00405480:	jne 25
0x00405482:	pushl $0x104<UINT32>
0x00405487:	leal %edi, 0x42a4ef(%ebp)
0x0040548d:	pushl %edi
0x0040548e:	pushl $0x0<UINT8>
0x00405490:	movl %ebx, $0x427243<UINT32>
0x00405495:	call GetModuleFileNameA@Kernel32.dll
GetModuleFileNameA@Kernel32.dll: API Node	
0x00405499:	jmp 0x004054b7
0x004054b7:	pushl $0x0<UINT8>
0x004054b9:	pushl $0x80<UINT32>
0x004054be:	pushl $0x3<UINT8>
0x004054c0:	pushl $0x0<UINT8>
0x004054c2:	pushl $0x1<UINT8>
0x004054c4:	pushl $0x80000000<UINT32>
0x004054c9:	pushl %edi
0x004054ca:	movl %ebx, $0x427247<UINT32>
0x004054cf:	call CreateFileA@Kernel32.dll
0x004054d3:	cmpl %eax, $0xffffffff<UINT8>
0x004054d6:	jne 0x004054df
0x004054df:	movl %edi, %eax
0x004054e1:	pushl $0x0<UINT8>
0x004054e3:	pushl %edi
0x004054e4:	movl %ebx, $0x427257<UINT32>
0x004054e9:	call GetFileSize@Kernel32.dll
GetFileSize@Kernel32.dll: API Node	
0x004054ed:	pushl %eax
0x004054ee:	pushl %edi
0x004054ef:	pushl %esi
0x004054f0:	movl %edi, 0x42a034(%ebp)
0x004054f6:	addl %edi, 0x3c(%edi)
0x004054f9:	movl %esi, %edi
0x004054fb:	addl %esi, $0x6<UINT8>
0x004054fe:	xorl %ecx, %ecx
0x00405500:	movw %cx, (%esi)
0x00405503:	decl %ecx
0x00405504:	addl %esi, $0xf2<UINT32>
0x0040550a:	movl %eax, $0x28<UINT32>
0x0040550f:	mull %eax, %ecx
0x00405511:	addl %esi, %eax
0x00405513:	addl %esi, $0x10<UINT8>
0x00405516:	movl %ecx, (%esi)
0x00405518:	popl %esi
0x00405519:	popl %edi
0x0040551a:	popl %eax
0x0040551b:	addl %eax, 0x42a044(%ebp)
0x00405521:	subl %eax, %ecx
0x00405523:	xchgl %esi, %eax
0x00405524:	pushl %esi
0x00405525:	pushl $0x40<UINT8>
0x00405527:	movl %ebx, $0x42724b<UINT32>
0x0040552c:	call GlobalAlloc@Kernel32.dll
GlobalAlloc@Kernel32.dll: API Node	
0x00405530:	cmpl %eax, $0x0<UINT8>
0x00405533:	jne 0x0040553a
0x0040553a:	xchgl %ebx, %eax
0x0040553b:	pushl $0x0<UINT8>
0x0040553d:	leal %eax, 0x42a4ef(%ebp)
0x00405543:	pushl %eax
0x00405544:	pushl %esi
0x00405545:	pushl %ebx
0x00405546:	pushl %edi
0x00405547:	movl %edx, $0x427253<UINT32>
0x0040554c:	call ReadFile@Kernel32.dll
ReadFile@Kernel32.dll: API Node	
0x00405550:	movl %eax, %ebx
0x00405552:	movl %ecx, %esi
0x00405554:	pushl %ebx
0x00405555:	pushl %edi
0x00405556:	pushl %ecx
0x00405557:	pushl %eax
0x00405558:	call 0x004055ce
