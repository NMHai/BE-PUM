0x00401000:	movl %eax, $0x404858<UINT32>
0x00401005:	pushl %eax
0x00401006:	pushl %fs:0
0x0040100d:	movl %fs:0, %esp
0x00401014:	xorl %eax, %eax
0x00401016:	movl (%eax), %ecx
0x00404858:	movl %eax, $0xf04035dd<UINT32>
0x0040485d:	leal %ecx, 0x1000129e(%eax)
0x00404863:	movl 0x1(%ecx), %eax
0x00404866:	movl %edx, 0x4(%esp)
0x0040486a:	movl %edx, 0xc(%edx)
0x0040486d:	movb (%edx), $0xffffffe9<UINT8>
0x00404870:	addl %edx, $0x5<UINT8>
0x00404873:	subl %ecx, %edx
0x00404875:	movl -4(%edx), %ecx
0x00404878:	xorl %eax, %eax
0x0040487a:	ret

0x00401016:	jmp 0x0040487b
0x0040487b:	movl %eax, $0xf04035dd<UINT32>
0x00404880:	popl %fs:0
0x00404887:	addl %esp, $0x4<UINT8>
0x0040488a:	pushl %ebp
0x0040488b:	pushl %ebx
0x0040488c:	pushl %ecx
0x0040488d:	pushl %edi
0x0040488e:	pushl %esi
0x0040488f:	pushl %edx
0x00404890:	leal %ebx, 0x10001257(%eax)
0x00404896:	movl %edx, 0x18(%ebx)
0x00404899:	pushl %edx
0x0040489a:	movl %ebp, %eax
0x0040489c:	pushl $0x40<UINT8>
0x0040489e:	pushl $0x1000<UINT32>
0x004048a3:	pushl 0x4(%ebx)
0x004048a6:	pushl $0x0<UINT8>
0x004048a8:	movl %ecx, 0x10(%ebx)
0x004048ab:	addl %ecx, %edx
0x004048ad:	movl %eax, (%ecx)
0x004048af:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x004048b1:	popl %edx
0x004048b2:	movl %edi, %eax
0x004048b4:	pushl %eax
0x004048b5:	pushl %edx
0x004048b6:	movl %esi, (%ebx)
0x004048b8:	movl %eax, 0x20(%ebx)
0x004048bb:	addl %eax, %edx
0x004048bd:	movl %ecx, (%eax)
0x004048bf:	movl 0x20(%ebx), %ecx
0x004048c2:	movl %eax, 0x1c(%ebx)
0x004048c5:	addl %eax, %edx
0x004048c7:	movl %ecx, (%eax)
0x004048c9:	movl 0x1c(%ebx), %ecx
0x004048cc:	addl %esi, %edx
0x004048ce:	movl %ecx, 0xc(%ebx)
0x004048d1:	addl %ecx, %edx
0x004048d3:	leal %eax, 0x1c(%ebx)
0x004048d6:	pushl %eax
0x004048d7:	pushl %edi
0x004048d8:	pushl %esi
0x004048d9:	call 0x0040477b
0x0040477b:	pusha
0x0040477c:	movl %esi, 0x24(%esp)
0x00404780:	movl %edi, 0x28(%esp)
0x00404784:	cld
0x00404785:	lodsl %eax, %ds:(%esi)
0x00404786:	xorl %ecx, %ecx
0x00404788:	testl %eax, %eax
0x0040478a:	je 17
0x0040478c:	xorl %edx, %edx
0x0040478e:	leal %ebx, (%eax,%edi)
0x00404791:	movsb %es:(%edi), %ds:(%esi)
0x00404792:	movb %cl, $0x3<UINT8>
0x00404794:	call 0x0040480b
0x0040480b:	addl %edx, %edx
0x0040480d:	jne 0x00404815
0x0040480f:	xchgl %edx, %eax
0x00404810:	lodsl %eax, %ds:(%esi)
0x00404811:	xchgl %edx, %eax
0x00404812:	addl %edx, %edx
0x00404814:	incl %edx
0x00404815:	ret

0x00404799:	jae 0x00404791
0x0040479b:	cmpl %edi, %ebx
0x0040479d:	jae 0x00404828
0x004047a3:	pushl %ebx
0x004047a4:	pushl %ebp
0x004047a5:	pushl %edi
0x004047a6:	xorl %ebx, %ebx
0x004047a8:	incl %ebx
0x004047a9:	xorl %ebp, %ebp
0x004047ab:	movl %eax, %ebx
0x004047ad:	leal %edi, (%ebp,%ebx)
0x004047b1:	movl %ebp, %ebx
0x004047b3:	movl %ebx, %edi
0x004047b5:	call 0x0040480b
0x004047ba:	jae 0x004047ad
0x004047bc:	leal %ebx, (%ebp,%edi)
0x004047c0:	addl %eax, %edi
0x004047c2:	movl %ebp, %edi
0x004047c4:	call 0x0040480b
0x004047c9:	jae 0x004047ad
0x004047cb:	popl %edi
0x004047cc:	popl %ebp
0x004047cd:	popl %ebx
0x004047ce:	subl %eax, %ecx
0x004047d0:	jae 0x004047db
0x004047db:	movb %cl, $0x6<UINT8>
0x004047dd:	call 0x0040480b
0x004047e2:	adcl %eax, %eax
0x004047e4:	decl %ecx
0x004047e5:	jne 0x004047dd
0x004047e7:	incl %eax
0x004047e8:	call 0x00404816
0x00404816:	xorl %ecx, %ecx
0x00404818:	incl %ecx
0x00404819:	call 0x0040480b
0x0040481e:	adcl %ecx, %ecx
0x00404820:	call 0x0040480b
0x00404825:	jb 0x00404819
0x00404827:	ret

0x004047ed:	movl %ebp, %eax
0x004047ef:	cmpl %eax, $0x8001<UINT32>
0x004047f4:	sbbl %ecx, $0xffffffff<UINT8>
0x004047f7:	cmpl %eax, $0x781<UINT32>
0x004047fc:	sbbl %ecx, $0xffffffff<UINT8>
0x004047ff:	pushl %esi
0x00404800:	movl %esi, %edi
0x00404802:	subl %esi, %eax
0x00404804:	rep movsb %es:(%edi), %ds:(%esi)
0x00404806:	popl %esi
0x00404807:	incl %ecx
0x00404808:	incl %ecx
0x00404809:	jmp 0x00404794
0x004047d2:	movl %eax, %ebp
0x004047d4:	call 0x00404816
0x004047d9:	jmp 0x004047ff
0x00404828:	subl %edi, 0x28(%esp)
0x0040482c:	movl 0x1c(%esp), %edi
0x00404830:	popa
0x00404831:	ret $0xc<UINT16>

0x004048db:	popl %edx
0x004048dc:	popl %eax
0x004048dd:	addl %eax, 0x8(%ebx)
0x004048e0:	movl %edi, %eax
0x004048e2:	pushl %edx
0x004048e3:	movl %esi, %eax
0x004048e5:	movl %eax, -4(%esi)
0x004048e8:	addl %eax, $0x4<UINT8>
0x004048eb:	subl %esi, %eax
0x004048ed:	movl 0x8(%esi), %edx
0x004048f0:	movl %ecx, 0xc(%ebx)
0x004048f3:	movl 0x14(%esi), %ecx
0x004048f6:	call 0x00020188
0x00020188:	pushl %ebx
0x00020189:	pushl %edi
0x0002018a:	pushl %esi
0x0002018b:	pushl %ebp
0x0002018c:	call 0x00020191
0x00020191:	popl %ebp
0x00020192:	subl %ebp, $0x1000134c<UINT32>
0x00020198:	leal %esi, 0x10001343(%ebp)
0x0002019e:	movl %eax, -4(%esi)
0x000201a1:	addl %eax, $0x4<UINT8>
0x000201a4:	subl %esi, %eax
0x000201a6:	cld
0x000201a7:	movl %ebx, %esi
0x000201a9:	movl %edx, 0x8(%esi)
0x000201ac:	movl %esi, 0x1c(%esi)
0x000201af:	addl %esi, %edx
0x000201b1:	leal %edi, 0x10002f2f(%ebp)
0x000201b7:	lodsl %eax, %ds:(%esi)
0x000201b8:	stosl %es:(%edi), %eax
0x000201b9:	lodsl %eax, %ds:(%esi)
0x000201ba:	stosl %es:(%edi), %eax
0x000201bb:	lodsl %eax, %ds:(%esi)
0x000201bc:	stosl %es:(%edi), %eax
0x000201bd:	lodsl %eax, %ds:(%esi)
0x000201be:	stosl %es:(%edi), %eax
0x000201bf:	nop
0x000201c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x000201c4:	je 21
0x000201c6:	movl %esi, 0x44(%ebx)
0x000201c9:	testl %esi, %esi
0x000201cb:	je 14
0x000201cd:	movl %ecx, $0x23<UINT32>
0x000201d2:	addl %esi, %edx
0x000201d4:	movl %edi, 0x40(%ebx)
0x000201d7:	addl %edi, %edx
0x000201d9:	rep movsb %es:(%edi), %ds:(%esi)
0x000201db:	movl %esi, %ebx
0x000201dd:	leal %edi, 0x10002f1b(%ebp)
0x000201e3:	addl (%edi), %ebp
0x000201e5:	addl 0x4(%edi), %ebp
0x000201e8:	addl 0x8(%edi), %ebp
0x000201eb:	leal %ecx, 0x10002eff(%ebp)
0x000201f1:	pushl %ecx
0x000201f2:	call 0x0002033d
0x0002033d:	pushl %ebp
0x0002033e:	movl %ebp, %esp
0x00020340:	addl %esp, $0xfffffffc<UINT8>
0x00020343:	pushl %ebx
0x00020344:	pushl %edi
0x00020345:	pushl %esi
0x00020346:	call 0x0002034b
0x0002034b:	popl %ebx
0x0002034c:	subl %ebx, $0x10001506<UINT32>
0x00020352:	movl %esi, 0x8(%ebp)
0x00020355:	movl %ecx, (%esi)
0x00020357:	addl %ecx, %ebx
0x00020359:	pushl %ecx
0x0002035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00020360:	movl -4(%ebp), %eax
0x00020363:	movl %edx, 0x4(%esi)
0x00020366:	movl %edi, 0x8(%esi)
0x00020369:	addl %edx, %ebx
0x0002036b:	addl %edi, %ebx
0x0002036d:	xorl %eax, %eax
0x0002036f:	addl %eax, (%edx)
0x00020371:	je 0x00020389
0x00020373:	pushl %edx
0x00020374:	movl %eax, (%edx)
0x00020376:	addl %eax, %ebx
0x00020378:	pushl %eax
0x00020379:	pushl -4(%ebp)
0x0002037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00020382:	stosl %es:(%edi), %eax
0x00020383:	popl %edx
0x00020384:	addl %edx, $0x4<UINT8>
0x00020387:	jmp 0x0002036d
0x00020389:	addl %esi, $0xc<UINT8>
0x0002038c:	addl %eax, (%esi)
0x0002038e:	jne 0x00020355
0x00020390:	popl %esi
0x00020391:	popl %edi
0x00020392:	popl %ebx
0x00020393:	leave
0x00020394:	ret $0x4<UINT16>

0x000201f7:	nop
0x000201f8:	nop
0x000201f9:	nop
0x000201fa:	nop
0x000201fb:	nop
0x000201fc:	nop
0x000201fd:	nop
0x000201fe:	nop
0x000201ff:	movl %ecx, 0x2c(%esi)
0x00020202:	movl 0x10002f2b(%ebp), %ecx
0x00020208:	pushl $0x4<UINT8>
0x0002020a:	pushl $0x1000<UINT32>
0x0002020f:	pushl %ecx
0x00020210:	pushl $0x0<UINT8>
0x00020212:	call VirtualAlloc@kernel32.dll
0x00020218:	movl 0x10002f27(%ebp), %eax
0x0002021e:	pushl %esi
0x0002021f:	call 0x0002061a
0x0002061a:	pushl %ebp
0x0002061b:	movl %ebp, %esp
0x0002061d:	addl %esp, $0xffffffe8<UINT8>
0x00020620:	pushl %ebx
0x00020621:	pushl %edi
0x00020622:	pushl %esi
0x00020623:	call 0x00020628
0x00020628:	popl %ebx
0x00020629:	subl %ebx, $0x100017e3<UINT32>
0x0002062f:	movl %esi, 0x8(%ebp)
0x00020632:	xorl %eax, %eax
0x00020634:	xorl %ecx, %ecx
0x00020636:	addl %ecx, 0x3c(%esi)
0x00020639:	je 10
0x0002063b:	movl %edx, 0x8(%esi)
0x0002063e:	movl %edi, %esi
0x00020640:	addl %esi, $0x50<UINT8>
0x00020643:	jmp 0x0002064c
0x0002064c:	movl -4(%ebp), %eax
0x0002064f:	movzwl %eax, 0x2(%edi)
0x00020653:	movl -16(%ebp), %eax
0x00020656:	pushl %ecx
0x00020657:	pushl %edx
0x00020658:	pushl %esi
0x00020659:	movzwl %eax, 0x10(%esi)
0x0002065d:	testl %eax, $0x10<UINT32>
0x00020662:	je 0x0002073e
0x0002073e:	popl %esi
0x0002073f:	popl %edx
0x00020740:	popl %ecx
0x00020741:	addl %esi, $0x1c<UINT8>
0x00020744:	decl %ecx
0x00020745:	jne 0x00020656
0x00020668:	pushl %esi
0x00020669:	movl %edi, 0x10002f27(%ebx)
0x0002066f:	movl -20(%ebp), %edi
0x00020672:	movl %ecx, 0x8(%esi)
0x00020675:	movl %eax, 0x14(%esi)
0x00020678:	subl %ecx, %eax
0x0002067a:	movl %esi, (%esi)
0x0002067c:	addl %esi, %edx
0x0002067e:	movl %eax, %ecx
0x00020680:	sarl %ecx, $0x2<UINT8>
0x00020683:	rep movsl %es:(%edi), %ds:(%esi)
0x00020685:	addl %ecx, %eax
0x00020687:	andl %ecx, $0x3<UINT8>
0x0002068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0002068c:	popl %esi
0x0002068d:	nop
0x0002068e:	nop
0x0002068f:	nop
0x00020690:	nop
0x00020691:	nop
0x00020692:	nop
0x00020693:	nop
0x00020694:	nop
0x00020695:	nop
0x00020696:	nop
0x00020697:	nop
0x00020698:	nop
0x00020699:	nop
0x0002069a:	nop
0x0002069b:	nop
0x0002069c:	nop
0x0002069d:	nop
0x0002069e:	nop
0x0002069f:	nop
0x000206a0:	nop
0x000206a1:	nop
0x000206a2:	nop
0x000206a3:	nop
0x000206a4:	nop
0x000206a5:	nop
0x000206a6:	nop
0x000206a7:	nop
0x000206a8:	nop
0x000206a9:	nop
0x000206aa:	nop
0x000206ab:	nop
0x000206ac:	nop
0x000206ad:	nop
0x000206ae:	nop
0x000206af:	nop
0x000206b0:	nop
0x000206b1:	nop
0x000206b2:	nop
0x000206b3:	movl %eax, 0x4(%esi)
0x000206b6:	addl %eax, %edx
0x000206b8:	movl -24(%ebp), %eax
0x000206bb:	movl %eax, -16(%ebp)
0x000206be:	decl %eax
0x000206bf:	movl -12(%ebp), %eax
0x000206c2:	pushl %edx
0x000206c3:	pushl %eax
0x000206c4:	pushl 0x8(%ebp)
0x000206c7:	call 0x00020a08
0x00020a08:	pushl %ebp
0x00020a09:	movl %ebp, %esp
0x00020a0b:	addl %esp, $0xfffffffc<UINT8>
0x00020a0e:	pushl %ebx
0x00020a0f:	pushl %edi
0x00020a10:	pushl %esi
0x00020a11:	movl %ebx, 0x8(%ebp)
0x00020a14:	movl %esi, %ebx
0x00020a16:	movl %ecx, 0x30(%ebx)
0x00020a19:	subl %esi, %ecx
0x00020a1b:	movl -4(%ebp), %esi
0x00020a1e:	xorl %ecx, %ecx
0x00020a20:	lodsl %eax, %ds:(%esi)
0x00020a21:	testl %eax, %eax
0x00020a23:	je 28
0x00020a25:	cmpl %ecx, 0xc(%ebp)
0x00020a28:	je 0x00020a2d
0x00020a2d:	nop
0x00020a2e:	nop
0x00020a2f:	nop
0x00020a30:	nop
0x00020a31:	nop
0x00020a32:	nop
0x00020a33:	nop
0x00020a34:	nop
0x00020a35:	nop
0x00020a36:	nop
0x00020a37:	nop
0x00020a38:	nop
0x00020a39:	nop
0x00020a3a:	nop
0x00020a3b:	nop
0x00020a3c:	nop
0x00020a3d:	nop
0x00020a3e:	addl %eax, -4(%ebp)
0x00020a41:	popl %esi
0x00020a42:	popl %edi
0x00020a43:	popl %ebx
0x00020a44:	leave
0x00020a45:	ret $0x8<UINT16>

0x000206cc:	leal %ecx, 0x10002f2f(%ebx)
0x000206d2:	pushl %ecx
0x000206d3:	pushl -24(%ebp)
0x000206d6:	pushl -20(%ebp)
0x000206d9:	call 0x00020008
0x00020008:	pusha
0x00020009:	movl %esi, 0x24(%esp)
0x0002000d:	movl %edi, 0x28(%esp)
0x00020011:	cld
0x00020012:	lodsl %eax, %ds:(%esi)
0x00020013:	xorl %ecx, %ecx
0x00020015:	testl %eax, %eax
0x00020017:	je 17
0x00020019:	xorl %edx, %edx
0x0002001b:	leal %ebx, (%eax,%edi)
0x0002001e:	movsb %es:(%edi), %ds:(%esi)
0x0002001f:	movb %cl, $0x3<UINT8>
0x00020021:	call 0x00020098
0x00020098:	addl %edx, %edx
0x0002009a:	jne 0x000200a2
0x0002009c:	xchgl %edx, %eax
0x0002009d:	lodsl %eax, %ds:(%esi)
0x0002009e:	xchgl %edx, %eax
0x0002009f:	addl %edx, %edx
0x000200a1:	incl %edx
0x000200a2:	ret

0x00020026:	jae 0x0002001e
0x00020028:	cmpl %edi, %ebx
0x0002002a:	jae 133
0x00020030:	pushl %ebx
0x00020031:	pushl %ebp
0x00020032:	pushl %edi
0x00020033:	xorl %ebx, %ebx
0x00020035:	incl %ebx
0x00020036:	xorl %ebp, %ebp
0x00020038:	movl %eax, %ebx
0x0002003a:	leal %edi, (%ebp,%ebx)
0x0002003e:	movl %ebp, %ebx
0x00020040:	movl %ebx, %edi
0x00020042:	call 0x00020098
0x00020047:	jae 0x0002003a
0x00020049:	leal %ebx, (%ebp,%edi)
0x0002004d:	addl %eax, %edi
0x0002004f:	movl %ebp, %edi
0x00020051:	call 0x00020098
0x00020056:	jae 0x0002003a
0x00020058:	popl %edi
0x00020059:	popl %ebp
0x0002005a:	popl %ebx
0x0002005b:	subl %eax, %ecx
0x0002005d:	jae 0x00020068
0x00020068:	movb %cl, $0x6<UINT8>
0x0002006a:	call 0x00020098
0x0002006f:	adcl %eax, %eax
0x00020071:	decl %ecx
0x00020072:	jne 0x0002006a
0x00020074:	incl %eax
0x00020075:	call 0x000200a3
0x000200a3:	xorl %ecx, %ecx
0x000200a5:	incl %ecx
0x000200a6:	call 0x00020098
0x000200ab:	adcl %ecx, %ecx
0x000200ad:	call 0x00020098
0x000200b2:	jb 0x000200a6
0x000200b4:	ret

0x0002007a:	movl %ebp, %eax
0x0002007c:	cmpl %eax, $0x8001<UINT32>
0x00020081:	sbbl %ecx, $0xffffffff<UINT8>
0x00020084:	cmpl %eax, $0x781<UINT32>
0x00020089:	sbbl %ecx, $0xffffffff<UINT8>
0x0002008c:	pushl %esi
0x0002008d:	movl %esi, %edi
0x0002008f:	subl %esi, %eax
0x00020091:	rep movsb %es:(%edi), %ds:(%esi)
0x00020093:	popl %esi
0x00020094:	incl %ecx
0x00020095:	incl %ecx
0x00020096:	jmp 0x00020021
0x0002005f:	movl %eax, %ebp
0x00020061:	call 0x000200a3
0x00020066:	jmp 0x0002008c
